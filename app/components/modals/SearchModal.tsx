'use client';

import qs from 'query-string';
import dynamic from 'next/dynamic';
import { useCallback, useMemo, useState } from "react";
import { Range } from 'react-date-range';
import { formatISO } from 'date-fns';
import { useRouter, useSearchParams } from 'next/navigation';

import useSearchModal from "@/app/hooks/useSearchModal";

import Modal from "./Modal";
import Calendar from "../inputs/Calendar";
import Counter from "../inputs/Counter";
import CitySelect, { CitySelectValue } from "../inputs/CitySelect";
import Heading from '../Heading';

import coords from '@/public/assets/coords.json'; // Import the coordinates file

enum STEPS {
  DEPARTURE = 0,
  ARRIVAL = 1,
  DATE = 2,
}

const SearchModal = () => {
  const router = useRouter();
  const searchModal = useSearchModal();
  const params = useSearchParams();

  const [step, setStep] = useState(STEPS.DEPARTURE);

  const [departure, setDeparture] = useState<CitySelectValue>();
  const [arrival, setArrival] = useState<CitySelectValue>();
  const [guestCount, setGuestCount] = useState(1);
  const [roomCount, setRoomCount] = useState(1);
  const [bathroomCount, setBathroomCount] = useState(1);
  const [dateRange, setDateRange] = useState<Range>({
    startDate: new Date(),
    endDate: new Date(),
    key: 'selection'
  });

  const Map = useMemo(() => dynamic(() => import('../Map'), {
    ssr: false
  }), [departure, arrival]);

  const getCityCoordinates = (cityName: string | undefined) => {
    if (!cityName) return null;
    const city = coords.find((c) => c.name === cityName);
    if (!city) return null;
    return [parseFloat(city.lat), parseFloat(city.lon)];
  };

  const onBack = useCallback(() => {
    setStep((value) => value - 1);
  }, []);

  const onNext = useCallback(() => {
    setStep((value) => value + 1);
  }, []);

  const onSubmit = useCallback(async () => {
    if (step !== STEPS.DATE) {
      return onNext();
    }

    let currentQuery = {};

    if (params) {
      currentQuery = qs.parse(params.toString());
    }

    const updatedQuery: any = {
      ...currentQuery,
      departure: departure?.value,
      arrival: arrival?.value,
      guestCount,
      roomCount,
      bathroomCount
    };

    if (dateRange.startDate) {
      updatedQuery.startDate = formatISO(dateRange.startDate);
    }

    if (dateRange.endDate) {
      updatedQuery.endDate = formatISO(dateRange.endDate);
    }

    const url = qs.stringifyUrl({
      url: '/',
      query: updatedQuery,
    }, { skipNull: true });

    setStep(STEPS.DEPARTURE);
    searchModal.onClose();
    router.push(url);
  }, [
    step,
    searchModal,
    departure,
    arrival,
    router,
    guestCount,
    roomCount,
    dateRange,
    onNext,
    bathroomCount,
    params
  ]);

  const actionLabel = useMemo(() => {
    if (step === STEPS.DATE) {
      return 'Search';
    }

    return 'Next';
  }, [step]);

  const secondaryActionLabel = useMemo(() => {
    if (step === STEPS.DEPARTURE) {
      return undefined;
    }

    return 'Back';
  }, [step]);

  let bodyContent = (
    <div className="flex flex-col gap-8">
      <Heading
        title="What's your departure city?"
        subtitle="Let's go explore our country"
      />
      <CitySelect
        value={departure}
        onChange={(value) =>
          setDeparture(value as CitySelectValue)}
      />
      <hr />
      <Map
        markers={[getCityCoordinates(departure?.label)].filter(Boolean) as [number, number][]}
      />
    </div>
  );

  if (step === STEPS.ARRIVAL) {
    bodyContent = (
      <div className="flex flex-col gap-8">
        <Heading
          title="Where do you wanna go?"
          subtitle="Find the perfect city!"
        />
        <CitySelect
          value={arrival}
          onChange={(value) =>
            setArrival(value as CitySelectValue)}
        />
        <hr />
        <Map
          markers={[
            getCityCoordinates(departure?.label),
            getCityCoordinates(arrival?.label),
          ].filter(Boolean) as [number, number][]}
        />
      </div>
    );
  }

  if (step === STEPS.DATE) {
    bodyContent = (
      <div className="flex flex-col gap-8">
        <Heading
          title="When are you traveling?"
          subtitle="Select your dates."
        />
        <Calendar
          onChange={(value) => setDateRange(value.selection)}
          value={dateRange}
        />
      </div>
    );
  }

  return (
    <Modal
      isOpen={searchModal.isOpen}
      title="Filters"
      actionLabel={actionLabel}
      onSubmit={onSubmit}
      secondaryActionLabel={secondaryActionLabel}
      secondaryAction={step === STEPS.DEPARTURE ? undefined : onBack}
      onClose={searchModal.onClose}
      body={bodyContent}
    />
  );
};

export default SearchModal;
