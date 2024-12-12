import React, { useEffect, useRef, useState } from 'react';

const GeoCoder = () => {
    const mapRef = useRef(null);
    const [map, setMap] = useState(null);
    const [startPoint, setStartPoint] = useState(null);
    const [endPoint, setEndPoint] = useState(null);
    const [userLocation, setUserLocation] = useState(null);
    const [routingService, setRoutingService] = useState(null);
    const apiKey = 'TE4uENSmw4i5u60Ec82fYcyzf5FDW8Zc-ZUyhXKUdCk';

    const busStations = [
        { id: 1, name: 'Gare Agdal', position: { lat: 34.000204, lng: -6.844855 } },
        { id: 2, name: 'Gare Hassan', position: { lat: 34.022895, lng: -6.833303 } },
        { id: 3, name: 'Gare Rabat Ville', position: { lat: 34.022447, lng: -6.837590 } },
        { id: 4, name: 'Gare Yacoub El Mansour', position: { lat: 33.998212, lng: -6.865556 } },
        { id: 5, name: 'Gare Hay Riad', position: { lat: 34.005238, lng: -6.826598 } },
    ];

    useEffect(() => {
        if (!apiKey) {
            const alertWarningUi = document.getElementById('alert-warning');
            if (alertWarningUi) alertWarningUi.style.display = 'block';
            return;
        }

        if (mapRef.current && window.H) {
            const platform = new window.H.service.Platform({
                apikey: apiKey,
            });

            const defaultLayers = platform.createDefaultLayers();

            const mapInstance = new window.H.Map(
                mapRef.current,
                defaultLayers.vector.normal.map,
                {
                    center: { lat: 34.020882, lng: -6.841650 },
                    zoom: 13,
                    pixelRatio: window.devicePixelRatio || 1,
                }
            );

            const mapEvents = new window.H.mapevents.MapEvents(mapInstance);
            new window.H.mapevents.Behavior(mapEvents);

            const ui = window.H.ui.UI.createDefault(mapInstance, defaultLayers);

            // Add bus stations as markers with names
            busStations.forEach((station) => {
                const icon = new window.H.map.Icon(
                    `https://img.icons8.com/color/48/bus.png`
                );
                const marker = new window.H.map.Marker(station.position, { icon });
                marker.setData(`<strong>${station.name}</strong>`);
                marker.addEventListener('tap', (evt) => {
                    const bubble = new window.H.ui.InfoBubble(evt.target.getGeometry(), {
                        content: evt.target.getData(),
                    });
                    ui.addBubble(bubble);
                });
                mapInstance.addObject(marker);
            });

            // Locate user's position
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    (position) => {
                        const { latitude, longitude } = position.coords;
                        setUserLocation({ lat: latitude, lng: longitude });

                        const userIcon = new window.H.map.Icon(
                            `https://img.icons8.com/emoji/48/blue-circle-emoji.png`
                        );

                        const userMarker = new window.H.map.Marker(
                            { lat: latitude, lng: longitude },
                            { icon: userIcon }
                        );
                        userMarker.setData('<strong>Vous êtes ici</strong>');
                        mapInstance.addObject(userMarker);

                        mapInstance.setCenter({ lat: latitude, lng: longitude });
                    },
                    (error) => {
                        console.error('Erreur de géolocalisation :', error);
                    }
                );
            }

            // Enable routing service
            const routing = platform.getRoutingService(null, 8);
            setRoutingService(routing);

            setMap(mapInstance);

            return () => mapInstance.dispose();
        }
    }, [apiKey]);

    const handleMapClick = (evt) => {
        const coord = map.screenToGeo(
            evt.currentPointer.viewportX,
            evt.currentPointer.viewportY
        );

        if (!startPoint) {
            setStartPoint(coord);
            addMarker(coord, 'Point de départ', 'green');
        } else if (!endPoint) {
            setEndPoint(coord);
            addMarker(coord, 'Destination', 'red');
            if (routingService) calculateRoute(startPoint, coord);
        } else {
            alert('Les points de départ et de destination sont déjà définis.');
        }
    };

    const addMarker = (coords, label, color) => {
        const icon = new window.H.map.Icon(
            `https://img.icons8.com/color/48/${color}-marker.png`
        );

        const marker = new window.H.map.Marker(coords, { icon });
        map.addObject(marker);
    };

    const calculateRoute = (start, end) => {
        const routeRequestParams = {
            routingMode: 'fast',
            transportMode: 'car',
            origin: `${start.lat},${start.lng}`,
            destination: `${end.lat},${end.lng}`,
            return: 'polyline',
        };

        routingService.calculateRoute(routeRequestParams, (result) => {
            if (result.routes.length) {
                const route = result.routes[0];
                const routeShape = route.sections[0].polyline;

                const linestring = new window.H.geo.LineString();
                routeShape.forEach((point) => {
                    const [lat, lng] = point.split(',');
                    linestring.pushLatLngAlt(lat, lng);
                });

                const routeLine = new window.H.map.Polyline(linestring, {
                    style: { strokeColor: 'blue', lineWidth: 4 },
                });

                map.addObject(routeLine);
            }
        });
    };

    const handleStartPointChange = () => {
        setStartPoint(null);
        setEndPoint(null);
        map.removeObjects(map.getObjects()); // Clear all markers and routes
    };

    const handleEndPointChange = () => {
        setEndPoint(null);
        map.removeObjects(map.getObjects()); // Clear all markers and routes
    };

    useEffect(() => {
        if (map) map.addEventListener('tap', handleMapClick);
        return () => {
            if (map) map.removeEventListener('tap', handleMapClick);
        };
    }, [map, startPoint, endPoint]);

    return (
        <div className="w-full max-w-4xl">
            <div
                ref={mapRef}
                style={{ width: '100%', height: '500px' }}
                className="map-container"
            />
            <div
                id="alert-warning"
                className="mt-2 p-2 bg-yellow-100 text-yellow-800 rounded"
                style={{ display: 'none' }}
            >
                <strong>Warning!</strong><br />
                Please paste your apiKey in the JavaScript code.
            </div>
            <div className="mt-4">
                <p>
                    <strong>Point de départ :</strong>{' '}
                    {startPoint
                        ? `${startPoint.lat.toFixed(4)}, ${startPoint.lng.toFixed(4)}`
                        : 'Non défini'}
                    <button onClick={handleStartPointChange}>Modifier</button>
                </p>
                <p>
                    <strong>Destination :</strong>{' '}
                    {endPoint
                        ? `${endPoint.lat.toFixed(4)}, ${endPoint.lng.toFixed(4)}`
                        : 'Non défini'}
                    <button onClick={handleEndPointChange}>Modifier</button>
                </p>
            </div>
        </div>
    );
};

export default GeoCoder;
