const { redisClient } = require('../services/redisService');

async function updateBusLocation(req, res) {
  const { busId, latitude, longitude } = req.body;

  if (!busId || !latitude || !longitude) {
    return res.status(400).json({ error: 'Missing busId, latitude, or longitude.' });
  }

  try {
    const timestamp = Date.now(); // Current time

    // Save the location data in Redis
    await redisClient.hSet(`bus:${busId}:location`, {
      latitude,
      longitude,
      timestamp,
    });

    res.status(200).json({ message: 'Bus location updated successfully.' });
  } catch (err) {
    console.error('Error updating bus location:', err);
    res.status(500).json({ error: 'Internal server error.' });
  }
}

async function getBusLocation(req, res) {
  const { busId } = req.params;

  try {
    const location = await redisClient.hGetAll(`bus:${busId}:location`);

    if (Object.keys(location).length === 0) {
      return res.status(404).json({ error: 'Bus location not found.' });
    }

    res.status(200).json({ busId, ...location });
  } catch (err) {
    console.error('Error fetching bus location:', err);
    res.status(500).json({ error: 'Internal server error.' });
  }
}

async function getAllBusLocations(req, res) {
  try {
    const keys = await redisClient.keys('bus:*:location');
    const buses = [];

    for (const key of keys) {
      const busId = key.split(':')[1];
      const location = await redisClient.hGetAll(key);
      buses.push({ busId, ...location });
    }

    res.status(200).json({ buses });
  } catch (err) {
    console.error('Error fetching all bus locations:', err);
    res.status(500).json({ error: 'Internal server error.' });
  }
}

module.exports = { updateBusLocation, getBusLocation, getAllBusLocations };
