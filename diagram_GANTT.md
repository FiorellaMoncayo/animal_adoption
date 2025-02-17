```mermaid
gantt
    title animal_adoption_TUONS
    dateFormat  YYYY-MM-DD

    section Planificación
    Definición del proyecto :done, 2025-02-06, 2025-02-16
    Investigación :done, 2025-02-10, 2025-02-18 
    Requisitos de usuario :done, plan, 2025-02-15, 2025-02-20   

    section Diseño
    Diseño UX/UI :d1, 2025-02-20, 2025-02-27
    Arquitectura Sistema :d2, 2025-02-27, 2025-03-06

    section Desarrollo Frontend
    Frontend :u0, 2025-03-07, 2025-03-19
    Crear modelo de usuario adoptante :u1, 2025-03-07, 2025-03-12
    Implementar creación de usuario :u2, 2025-03-09, 2025-03-15
    Implementar lectura de usuario  :u3, 2025-03-09, 2025-03-14
    Implementar actualización de usuario :u4, 2025-03-09, 2025-03-19
    Implementar eliminación de usuario :u5, 2025-03-09, 2025-03-17

    
    Crear modelo de refugio         :r1, 2025-03-20, 2025-03-30
    Implementar creación de refugio :r2, 2025-03-22, 2025-04-03
    Implementar lectura de refugio  :r3, 2025-03-22, 2025-04-01
    Implementar actualización de refugio :r4, 2025-03-22, 2025-04-06
    Implementar eliminación de refugio :r5, 2025-03-22, 2025-04-02

    
    Crear modelo de animal         :a1, 2025-04-06, 2025-04-11
    Implementar creación de animal :a2, 2025-04-08, 2025-04-19
    Implementar lectura de animal  :a3, 2025-04-08, 2025-04-15
    Implementar actualización de animal :a4, 2025-04-08, 2025-04-21
    Implementar eliminación de animal :a5, 2025-04-08, 2025-04-16

    section Desarrollo Backend

    Desarrollo Backend :d1, 2025-04-17, 2025-05-17
