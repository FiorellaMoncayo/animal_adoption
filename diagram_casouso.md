```mermaid
graph TD

    %% Actores principales
    Usuario(("**🧑 USUARIO**"))
    Refugio(("**🏠 REFUGIO**"))
    Tuons["**🔗 TUONS**"]

    %% Casos de uso del Usuario
    subgraph "👤 **Acciones del Usuario**"
        A["🔍 **Busco un animal**"]
        C["📜 **Registro (Sign)**"]
        D["🔑 **Inicio sesión (Log)**"]
        H["📞 **Solicito contacto con refugio**"]
        E["✏️ **Actualizo datos (Upd)**"]
        F["❌ **Elimino mi cuenta (Del)**"]
        G["👀 **Veo animales disponibles**"]        
    end
    
    %% Casos de uso del Refugio
    subgraph "🏠 **Acciones del Refugio**"
        I["📝 **Publico un animal**"]
        J["🔀 **Asigno un animal**"]
        K["📜 **Registro (Sign)**"]
        L["🔑 **Inicio sesión (Log)**"]
        M["✏️ **Actualizo datos Refujio (Upd)**"]
        N["✏️ **Actualizo datos Animal (Upd)**"]
        O["❌ **Elimino mi Refujio (Del)**"]
        P["❌ **Elimino Animal (Del)**"]
    end

    %% Conexiones
    Usuario -->|"📌 Realiza acciones"| A
    Usuario --> C
    Usuario --> D
    Usuario --> E
    Usuario --> F
    Usuario --> G
    Usuario --> H
    H -->|"📡 Envía solicitud"| Tuons
    Tuons -->|"📨 Contacta al refugio"| Refugio
    Tuons -->|"📤 Responde al usuario"| Usuario
    
    Refugio --> I
    Refugio --> J
    Refugio --> K
    Refugio --> L
    Refugio --> M
    Refugio --> N
    Refugio --> O
    Refugio --> P
    Refugio -->|"📡 Interactúa con TUONS"| Tuons

