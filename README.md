# Twitch Viewer Statistics

This project provides a simple system for tracking Twitch channel viewer statistics. It consists of:

- **Kotlin/Spring Boot backend** that fetches viewer counts via the Twitch API and stores history in PostgreSQL.
- **Vue 3 frontend** to trigger refreshes and display stored statistics.

## Backend

Located in the `backend` directory.

### Run

```bash
cd backend
./gradlew bootRun
```

The backend expects a PostgreSQL database available at `jdbc:postgresql://localhost:5432/twitchstats`. Twitch API credentials must be supplied through `TWITCH_CLIENT_ID` and `TWITCH_ACCESS_TOKEN` environment variables.

## Frontend

Located in the `frontend` directory. Uses Vite for development.

### Run

```bash
cd frontend
npm install
npm run dev
```

The dev server proxies API requests to the backend running on `localhost:8080`.
