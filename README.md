# Nude Eye Project
### Object-Oriented Programming and Design — S1 Project
**Group:** S1-Project-186  
**Authors:** Solange Bel & Pau Gómez

---

## How to run
Open the project in IntelliJ IDEA and run `src/Presentation/main.java`.
Make sure all libraries in the `lib/` folder are added to the project classpath.

## JavaDoc
The generated JavaDoc is located at `out/javadoc/index.html`.
Open it in any browser to browse the full documentation.

## Project structure
- `src/` — Java source code (Presentation, Buisness, Persistance layers)
- `lib/` — External libraries (Gson, ApiHelper, CoordinatesAPI, OpenCSV)
- `out/javadoc/` — Generated JavaDoc
- `src/Resources/` — Local fallback data files (clients.json, products.json, providers.json, sales.csv)

## Notes
The program connects to the API at startup (`https://balandrau.salle.url.edu/dpoo`).
If the API is unavailable, it automatically falls back to the local files in `src/Resources/`.
The API is only accessible from the university network or VPN.