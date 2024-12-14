const express = require('express');
const {
  updateBusLocation,
  getBusLocation,
  getAllBusLocations,
} = require('../controllers/busLocationController');

const router = express.Router();

// Routes
router.post('/location', updateBusLocation);
router.get('/:busId', getBusLocation);
router.get('/locations', getAllBusLocations);

module.exports = router;
