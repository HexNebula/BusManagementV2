'use client';

import Select from 'react-select';
import cities from '@/public/assets/cities.json'; // Adjust the path to your JSON file

export type CitySelectValue = {
  value: string; // City ID
  label: string; // City Name
};

interface CitySelectProps {
  value?: CitySelectValue;
  onChange: (value: CitySelectValue) => void;
}

const CitySelect: React.FC<CitySelectProps> = ({ value, onChange }) => {
  // Map static city data to react-select options
  const cityOptions = cities.map((city) => ({
    value: city.id, // Use the city ID as the unique value
    label: city.name, // Use the city name as the display label
  }));

  return (
    <div>
      <Select
        placeholder="Start typing a city..."
        isClearable
        options={cityOptions} // Provide the city options
        value={value}
        onChange={(value) => onChange(value as CitySelectValue)} // Handle selection
        formatOptionLabel={(option: any) => (
          <div className="flex flex-row items-center gap-3">
            <div>{option.label}</div>
          </div>
        )}
        classNames={{
          control: () => 'p-3 border-2',
          input: () => 'text-lg',
          option: () => 'text-lg',
        }}
        theme={(theme) => ({
          ...theme,
          borderRadius: 6,
          colors: {
            ...theme.colors,
            primary: 'black',
            primary25: '#ffe4e6',
          },
        })}
      />
    </div>
  );
};

export default CitySelect;
