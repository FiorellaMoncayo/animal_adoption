```mermaid
graph TD

    %% Main Actors
    User(("**🧑 USER**"))
    Shelter(("**🏠 SHELTER**"))
    Tuons["**📱 TUONS**"]

    %% User Use Cases
    subgraph "👤 **User Actions**"
        A["🔍 **Search for an animal**"]
        C["📜 **Register (Sign up)**"]
        D["🔑 **Log in**"]
        H["📞 **Request contact with shelter**"]
        E["✏️ **Update profile (Upd)**"]
        F["❌ **Delete my account (Del)**"]
        G["👀 **View available animals**"]        
    end
    
    %% Shelter Use Cases
    subgraph "🏠 **Shelter Actions**"
        I["📝 **Post an animal**"]
        J["🔀 **Assign an animal**"]
        K["📜 **Register (Sign up)**"]
        L["🔑 **Log in**"]
        M["✏️ **Update shelter details (Upd)**"]
        N["✏️ **Update animal details (Upd)**"]
        O["❌ **Delete shelter (Del)**"]
        P["❌ **Delete animal (Del)**"]
    end

    %% Connections
    User -->|"📌 Performs actions"| A
    User --> C
    User --> D
    User --> E
    User --> F
    User --> G
    User --> H
    H -->|"📡 Sends request"| Tuons
    Tuons -->|"📨 Contacts the shelter"| Shelter
    Tuons -->|"📤 Responds to user"| User
    
    Shelter --> I
    Shelter --> J
    Shelter --> K
    Shelter --> L
    Shelter --> M
    Shelter --> N
    Shelter --> O
    Shelter --> P
    Shelter -->|"📡 Interacts with TUONS"| Tuons
