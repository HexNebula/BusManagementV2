'use client';

import L from 'leaflet';
import { MapContainer, Marker, TileLayer, useMap } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet-routing-machine';
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';
import { useEffect } from 'react';

// @ts-ignore
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconUrl: markerIcon.src,
  iconRetinaUrl: markerIcon2x.src,
  shadowUrl: markerShadow.src,
});

interface MapProps {
  markers?: [number, number][]; // Ensure markers are tuples of [lat, lng]
}

const url = "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
const attribution = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';

const Routing = ({ markers }: { markers: [number, number][] }) => {
  const map = useMap();

  useEffect(() => {
    if (markers.length === 2) {
      const [start, end] = markers;

      const routingControl = L.Routing.control({
        waypoints: [L.latLng(start), L.latLng(end)],
        lineOptions: {
          styles: [{ color: 'blue', weight: 4, opacity: 0.7 }],
        },
        addWaypoints: false,
        draggableWaypoints: false,
        routeWhileDragging: false,
        showAlternatives: false,
      }).addTo(map);

      return () => {
        map.removeControl(routingControl);
      };
    }
  }, [markers, map]);

  return null;
};

const Map: React.FC<MapProps> = ({ markers = [] }) => {
  return (
    <MapContainer
      center={markers[0] || [32.25677, -4.498189]} // Default center
      zoom={6} // Slightly zoomed-in level
      scrollWheelZoom={false}
      className="h-[35vh] rounded-lg"
    >
      <TileLayer url={url} attribution={attribution} />

      {/* Render markers */}
      {markers.map((marker, index) => (
        <Marker key={index} position={marker as L.LatLngExpression} />
      ))}

      {/* Render route */}
      {markers.length === 2 && <Routing markers={markers} />}
    </MapContainer>
  );
};

export default Map;
