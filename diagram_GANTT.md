Diagrama de Gantt del Proyecto

```mermaid
gantt
    title animal_adoption_TUONS
    dateFormat  DD-MM

    section Planning
    Project Definition :done, 06-02, 16-02
    Research :done, 10-02, 18-02
    User Requirements :done, plan, 15-02, 20-02   

    section Design
    UX/UI Design :d1, 20-02, 22-02
    System Architecture :d2, 22-02, 24-02

    section Backend Development
    Backend :u0, 22-02, 28-03
    Create Adopter User Model :u1, 22-02, 27-02
    Implement User Creation :u2, 24-02, 05-03
    Implement User Reading :u3, 24-02, 01-03
    Implement User Update :u4, 24-02, 05-03
    Implement User Deletion :u5, 24-02, 03-03

    Create Shelter Model :r1, 06-03, 16-03
    Implement Shelter Creation :r2, 08-03, 19-03
    Implement Shelter Reading :r3, 08-03, 17-03
    Implement Shelter Update :r4, 08-03, 20-03
    Implement Shelter Deletion :r5, 08-03, 18-03

    Create Animal Model :a1, 18-03, 23-03
    Implement Animal Creation :a2, 18-03, 28-03
    Implement Animal Reading :a3, 18-03, 25-03
    Implement Animal Update :a4, 18-03, 28-03
    Implement Animal Deletion :a5, 18-03, 25-03

    section Frontend Development
    Frontend Development :d1, 28-03, 30-04
    Create Screens :d2, 28-03, 06-04
    Connect Backend with Frontend: d3, 06-04, 20-04
    Final Frontend Touch-ups: d4, 20-04, 30-04

    section Testing
    Unit Testing :p1, 30-04, 07-05
    Integration Testing :p2, 07-05, 14-05
    Bug Fixing :p3, 14-05, 18-05

    section Deployment
    Production Implementation :i1, 18-05, 21-05
    Maintenance and Improvements :i2, 21-05, 31-05

