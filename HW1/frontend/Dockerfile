# Use a Node.js base image
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
