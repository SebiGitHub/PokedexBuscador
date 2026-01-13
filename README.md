# PokedexBuscador

## Qué es
App Android en Kotlin que consume una API pública de Pokémon para buscar criaturas por nombre/ID y mostrar información detallada (tipos, habilidades, sprites, etc.).

## Stack
- Kotlin
- Android Studio
- API: PokeAPI (REST)
- Parsing: Gson / Kotlinx Serialization
- Arquitectura: MVVM + ViewModel + LiveData/Flow

## Features
- Buscador por nombre/ID con validaciones
- Vista de detalle con stats (tipos, habilidades, peso/altura, sprites)
- Manejo de estados: loading / error / vacío

## Capturas/GIF
<img width="547" height="1244" alt="Captura de pantalla 2026-01-13 123634" src="https://github.com/user-attachments/assets/cd48653c-e002-4268-8de4-6f5ee5c3bf25" />
<img width="552" height="1245" alt="Captura de pantalla 2026-01-13 123727" src="https://github.com/user-attachments/assets/666b7deb-af18-4775-8a99-ec3fc9654c63" />

## Cómo ejecutar
1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza Gradle (Gradle Sync)
4. Ejecuta en emulador o dispositivo (Run ▶️)
5. (Opcional) Si usas API key o config:
   - Crea `secrets.properties` con:
     API_KEY=tu_clave
   - En Gradle se lee esa variable para usarla en la app.

## Qué aprendí
- Consumir APIs REST desde Android y mapear respuestas a modelos Kotlin
- Diseñar UI/UX de búsqueda: errores, vacíos, loading y estados consistentes
- Separar responsabilidades (data / domain / UI) para mantener el proyecto limpio
- Buenas prácticas: evitar hardcodeo, manejo de errores y código testeable
