# Use a Node.js base image
FROM node:20 AS build

# Set the working directory in the Docker container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your app's source code
COPY . .

# Build your app
RUN npm run build

# Use nginx to serve the static files
FROM nginx:alpine

# Copy static assets from builder stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80 to the outside
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
