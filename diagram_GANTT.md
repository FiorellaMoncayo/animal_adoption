```mermaid
gantt
    title animal_adoption_TUONS
    dateFormat  YYYY-MM-DD
    section Planificación
    Planificación del proyecto      :done, plan, 2025-02-15, 2025-02-20

    section Instalación & Config entorno
    Crear entorno de desarroyo :d1, 2025-02-20, 2025-03-06

    section Desarrollo - Usuario
    Crear modelo de usuario adoptante :u1, 2025-03-07, 2025-03-12
    Implementar creación de usuario :u2, 2025-03-09, 2025-03-15
    Implementar lectura de usuario  :u3, 2025-03-09, 2025-03-14
    Implementar actualización de usuario :u4, 2025-03-09, 2025-03-19
    Implementar eliminación de usuario :u5, 2025-03-09, 2025-03-17

    section Desarrollo - Refugio
    Crear modelo de refugio         :r1, 2025-03-20, 2025-03-30
    Implementar creación de refugio :r2, 2025-03-22, 2025-04-03
    Implementar lectura de refugio  :r3, 2025-03-22, 2025-04-01
    Implementar actualización de refugio :r4, 2025-03-22, 2025-04-06
    Implementar eliminación de refugio :r5, 2025-03-22, 2025-04-02

    section Desarrollo - Animal
    Crear modelo de animal         :a1, 2025-04-06, 2025-04-11
    Implementar creación de animal :a2, 2025-04-08, 2025-04-19
    Implementar lectura de animal  :a3, 2025-04-08, 2025-04-15
    Implementar actualización de animal :a4, 2025-04-08, 2025-04-21
    Implementar eliminación de animal :a5, 2025-04-08, 2025-04-16
