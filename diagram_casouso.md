´´´mermaid
graph TD

    %% Actores principales
    Usuario(("**🧑 USUARIO**"))
    Refugio(("**🏠 REFUGIO**"))
    Tuons["**🔗 TUONS**"]

    %% Casos de uso del Usuario
    subgraph "👤 **Acciones del Usuario**"
        A["🔍 **Busco un animal**"]
        B["📩 **Pido un animal**"]
        C["📜 **Registro (Sign)**"]
        D["🔑 **Inicio sesión (Log)**"]
        E["✏️ **Actualizo datos (Upd)**"]
        F["❌ **Elimino mi cuenta (Del)**"]
        G["👀 **Veo animales disponibles**"]
        H["📞 **Solicito contacto con refugio**"]
    end
    
    %% Casos de uso del Refugio
    subgraph "🏠 **Acciones del Refugio**"
        I["📝 **Publico un animal**"]
        J["🔀 **Asigno un animal**"]
        K["📜 **Registro (Sign)**"]
        L["🔑 **Inicio sesión (Log)**"]
        M["✏️ **Actualizo datos (Upd)**"]
        N["❌ **Elimino datos (Del)**"]
    end

    %% Conexiones
    Usuario -->|"📌 Realiza acciones"| A
    Usuario --> B
    Usuario --> C
    Usuario --> D
    Usuario --> E
    Usuario --> F
    Usuario --> G
    Usuario --> H
    H -->|"📡 Envía solicitud"| Tuons
    Tuons -->|"📨 Contacta al refugio"| Refugio
    
    Refugio --> I
    Refugio --> J
    Refugio --> K
    Refugio --> L
    Refugio --> M
    Refugio --> N
