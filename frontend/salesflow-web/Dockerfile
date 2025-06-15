# 1. build
FROM node:20-alpine AS build
WORKDIR /app
COPY . .
RUN corepack enable && pnpm i && pnpm run build

# 2. nginx
FROM nginx:1.25-alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf