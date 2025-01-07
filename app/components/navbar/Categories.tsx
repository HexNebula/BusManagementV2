'use client';

import { usePathname, useSearchParams } from 'next/navigation';
import { TbBeach, TbMountain, TbPool } from 'react-icons/tb';
import {
  GiBarn,
  GiBoatFishing,
  GiCactus,
  GiCastle,
  GiCaveEntrance,
  GiForestCamp,
  GiIsland,
  GiWindmill
} from 'react-icons/gi';
import { FaSkiing } from 'react-icons/fa';
import { BsSnow } from 'react-icons/bs';
import { IoDiamond } from 'react-icons/io5';
import { MdOutlineVilla } from 'react-icons/md';

import CategoryBox from "../CategoryBox";
import Container from '../Container';


export const categories = [
  {
    label: 'Beach',
    icon: TbBeach,
    description: 'This city is known for its beautiful beaches!',
  },
  {
    label: 'Windmills',
    icon: GiWindmill,
    description: 'This city features historic windmills and scenic views!',
  },
  {
    label: 'Modern',
    icon: MdOutlineVilla,
    description: 'This city is a hub of modern architecture and culture!'
  },
  {
    label: 'Countryside',
    icon: TbMountain,
    description: 'This city is surrounded by serene countryside landscapes!'
  },
  {
    label: 'Pools',
    icon: TbPool,
    description: 'This city offers luxurious pools and relaxing resorts!'
  },
  {
    label: 'Islands',
    icon: GiIsland,
    description: 'This city is located on a picturesque island!'
  },
  {
    label: 'Lake',
    icon: GiBoatFishing,
    description: 'This city is near a tranquil lake with stunning views!'
  },
  {
    label: 'Skiing',
    icon: FaSkiing,
    description: 'This city is famous for its skiing and winter sports!'
  },
  {
    label: 'Castles',
    icon: GiCastle,
    description: 'This city is home to historic and majestic castles!'
  },
  {
    label: 'Caves',
    icon: GiCaveEntrance,
    description: 'This city features mysterious and fascinating caves!'
  },
  {
    label: 'Camping',
    icon: GiForestCamp,
    description: 'This city offers great spots for camping and outdoor adventures!'
  },
  {
    label: 'Arctic',
    icon: BsSnow,
    description: 'This city is located in an Arctic environment with snowy landscapes!'
  },
  {
    label: 'Desert',
    icon: GiCactus,
    description: 'This city is situated in the heart of the desert!'
  },
  {
    label: 'Barns',
    icon: GiBarn,
    description: 'This city is surrounded by rustic barns and farmlands!'
  },
  {
    label: 'Lux',
    icon: IoDiamond,
    description: 'This city is known for its luxury and exclusivity!'
  }
];

const Categories = () => {
  const params = useSearchParams();
  const category = params?.get('category');
  const pathname = usePathname();
  const isMainPage = pathname === '/';

  if (!isMainPage) {
    return null;
  }

  return (
    <Container>
      <div
        className="
          pt-4
          flex 
          flex-row 
          items-center 
          justify-between
          overflow-x-auto
        "
      >
        {categories.map((item) => (
          <CategoryBox
            key={item.label}
            label={item.label}
            icon={item.icon}
            selected={category === item.label}
          />
        ))}
      </div>
    </Container>
  );
}

export default Categories;
