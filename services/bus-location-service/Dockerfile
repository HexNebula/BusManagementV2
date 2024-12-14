# Use Node.js LTS version
FROM node:16

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json for dependency installation
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy all application files into the container
COPY . .

# Expose port for the Node.js app
EXPOSE 3000

# Start the application
CMD ["node", "app.js"]
