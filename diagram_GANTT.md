Diagrama de Gantt del Proyecto

```mermaid
gantt
    title animal_adoption_TUONS
    dateFormat  YYYY-MM-DD

    section Planificación
    Definición del proyecto :done, 2025-02-06, 2025-02-16
    Investigación :done, 2025-02-10, 2025-02-18 
    Requisitos de usuario :done, plan, 2025-02-15, 2025-02-20   

    section Diseño
    Diseño UX/UI :d1, 2025-02-20, 2025-02-22
    Arquitectura Sistema :d2, 2025-02-22, 2025-02-24

    section Desarrollo Backend
    Backend :u0, 2025-02-22, 2025-04-07
    Crear modelo de usuario adoptante :u1, 2025-02-22, 2025-02-27
    Implementar creación de usuario :u2, 2025-02-24, 2025-03-02
    Implementar lectura de usuario  :u3, 2025-02-24, 2025-03-01
    Implementar actualización de usuario :u4, 2025-02-24, 2025-03-05
    Implementar eliminación de usuario :u5, 2025-02-24, 2025-03-03

    Crear modelo de refugio         :r1, 2025-03-06, 2025-03-16
    Implementar creación de refugio :r2, 2025-03-08, 2025-03-20
    Implementar lectura de refugio  :r3, 2025-03-08, 2025-03-17
    Implementar actualización de refugio :r4, 2025-03-08, 2025-03-22
    Implementar eliminación de refugio :r5, 2025-03-08, 2025-03-18

    Crear modelo de animal         :a1, 2025-03-23, 2025-03-28
    Implementar creación de animal :a2, 2025-03-25, 2025-04-05
    Implementar lectura de animal  :a3, 2025-03-25, 2025-04-01
    Implementar actualización de animal :a4, 2025-03-25, 2025-04-07
    Implementar eliminación de animal :a5, 2025-03-25, 2025-04-02

    section Desarrollo Frontend

    Desarrollo Frontend :d1, 2025-04-02, 2025-05-02

    section Pruebas
    Pruebas unitarias :p1, 2025-05-21, 2025-05-27
    Pruebas de integración :p2, 2025-05-27, 2025-06-02
    Corrección de errores :p3, 2025-06-02, 2025-06-05

    section Despliegue
    Implementación en producción :i1, 2025-06-05, 2025-06-13
    Mantenimiento y mejoras :i2, 2025-06-13, 2025-06-30

