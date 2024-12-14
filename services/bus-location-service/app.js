const express = require('express');
const bodyParser = require('body-parser');
const { connectRedis } = require('./services/redisService');
const busLocationRoutes = require('./routes/busLocationRoutes');

// Initialize Express App
const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use('/api/bus', busLocationRoutes);

(async () => {
  await connectRedis(); // Connect to Redis
  app.listen(PORT, () => {
    console.log(`Bus Location Service running on port ${PORT}`);
  });
})();
