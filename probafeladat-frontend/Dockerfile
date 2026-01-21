# Build stage
FROM node:20 AS build
WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Build arguments for environment
ARG API_URL=http://localhost:8081
ARG SZAMLAZZ_API_KEY=

# Generate environment.ts from template
RUN cp src/environments/environment.template.ts src/environments/environment.ts && \
    sed -i "s|__API_URL__|${API_URL}|g" src/environments/environment.ts && \
    sed -i "s|__SZAMLAZZ_API_KEY__|${SZAMLAZZ_API_KEY}|g" src/environments/environment.ts

# Build the application
RUN npm run build

# Run stage
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

# Remove default nginx files
RUN rm -rf ./*

# Copy built application from build stage
COPY --from=build /app/dist/probafeladat-frontend/browser .

# Copy nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
