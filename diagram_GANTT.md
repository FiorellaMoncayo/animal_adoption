Diagrama de Gantt del Proyecto

```mermaid
gantt
    title animal_adoption_TUONS
    dateFormat  DD-MM

    section Planificación
    Definición del proyecto :done, 06-02, 16-02
    Investigación :done, 10-02, 18-02
    Requisitos de usuario :done, plan, 15-02, 20-02   

    section Diseño
    Diseño UX/UI :d1, 20-02, 22-02
    Arquitectura Sistema :d2, 22-02, 24-02

    section Desarrollo Backend
    Backend :u0, 22-02, 05-04
    Crear modelo de usuario adoptante :u1, 22-02, 27-02
    Implementar creación de usuario :u2, 24-02, 05-03
    Implementar lectura de usuario  :u3, 24-02, 01-03
    Implementar actualización de usuario :u4, 24-02, 05-03
    Implementar eliminación de usuario :u5, 24-02, 03-03

    Crear modelo de refugio         :r1, 06-03, 16-03
    Implementar creación de refugio :r2, 08-03, 19-03
    Implementar lectura de refugio  :r3, 08-03, 17-03
    Implementar actualización de refugio :r4, 08-03, 20-03
    Implementar eliminación de refugio :r5, 08-03, 18-03

    Crear modelo de animal         :a1, 23-03, 28-03
    Implementar creación de animal :a2, 25-03, 05-04
    Implementar lectura de animal  :a3, 25-03, 01-04
    Implementar actualización de animal :a4, 25-03, 05-04
    Implementar eliminación de animal :a5, 25-03, 02-04

    section Desarrollo Frontend
    Desarrollo Frontend :d1, 05-04, 02-05

    section Pruebas
    Pruebas unitarias :p1, 02-05, 09-05
    Pruebas de integración :p2, 09-05, 16-05
    Corrección de errores :p3, 16-05, 20-05

    section Despliegue
    Implementación en producción :i1, 20-05, 23-05
    Mantenimiento y mejoras :i2, 23-05, 31-05

