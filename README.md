Привет! Меня зовут Феодор Маслов, и я являюсь Android-разработчиком, специализирующимся на использовании Kotlin. В данном репозитории представлен мой проект FlightFinder.

# FlightFinder

FlightFinder - это приложение для поиска дешевых авиабилетов. Этот проект демонстрирует мои навыки в следующих областях:

### Работа с сетью
Я использовал Retrofit для реализации сетевых запросов и получения данных о билетах от сервера. Это позволяет приложению эффективно взаимодействовать с удаленными API.

### Многопоточность и асинхронность
Я использовал корутины и Flow для выполнения асинхронных операций, таких как сетевые запросы и обработка данных, что обеспечивает плавную работу приложения без блокировки основного потока.

### Dependency Injection (DI)
Я использовал Dagger Hilt для управления зависимостями в приложении, что повышает модульность и тестируемость кода. Это также упрощает управление жизненным циклом зависимостей и делает код более чистым и поддерживаемым.

### MVVM и Clean Architecture
Я применил паттерн MVVM (Model-View-ViewModel) и принципы чистой архитектуры, разделив приложение на несколько слоев: presentation, domain и data. Это обеспечивает высокую масштабируемость, тестируемость и поддерживаемость кода.

- **Presentation Layer**: Содержит UI-компоненты, такие как экраны поиска и результатов, а также ViewModel для управления состоянием UI.
- **Domain Layer**: Включает use cases и бизнес-логику, обеспечивающую выполнение основных операций приложения.
- **Data Layer**: Содержит репозитории, источники данных и модели данных, используемые для работы с сетью и кэшированием.

### Многомодульность
Я реализовал многомодульную архитектуру, разделив приложение на несколько модулей: app, core, data, domain, feature-main, feature-search и feature-tickets. Это улучшает изоляцию компонентов, облегчает тестирование и способствует повторному использованию кода. Такой подход также ускоряет сборку проекта и повышает его масштабируемость.

### Работа с пользовательским интерфейсом
Я использовал обычную XML-разметку для создания пользовательского интерфейса, что обеспечивает гибкость и контроль над каждым элементом UI. Все шрифты, цвета и иконки взяты из макетов в Figma для обеспечения идентичности дизайна.

- **UI-компоненты**: Приложение содержит несколько экранов, включая главный экран поиска, экран с результатами поиска и модальные окна для ввода данных.
- **Навигация**: Применена навигация с использованием FragmentTransaction, что позволяет легко управлять переходами между экранами и обеспечивает плавный пользовательский опыт.

### Хранение данных
Для сохранения данных о последнем введенном пользователем значении и предпочтениях я использовал Shared Preferences. Это позволяет сохранять пользовательские настройки и данные даже после перезапуска приложения.


### Сборка
Сборка стандартная: (Build -> Build App Bundle(s) / APK(s) -> Build APK(s))

Я надеюсь, что этот проект продемонстрирует мои навыки и опыт в разработке Android-приложений с использованием Kotlin. Буду рад ответить на любые вопросы или обсудить дальнейшие детали. Спасибо за рассмотрение моей кандидатуры!

*************************

Hello! My name is Feodor Maslov, and I am an Android developer specializing in Kotlin. This repository presents my project FlightFinder.

# FlightFinder

FlightFinder is an application for searching for cheap airline tickets. This project showcases my experience and skills in the following areas:

### Network Operations
I used Retrofit to implement network requests and retrieve ticket data from the server. This allows the application to efficiently interact with remote APIs.

### Multithreading and Asynchrony
I used coroutines and Flow to perform asynchronous operations such as network requests and data processing, ensuring smooth application performance without blocking the main thread.

### Dependency Injection (DI)
I used Dagger Hilt to manage dependencies in the application, enhancing code modularity and testability. It also simplifies dependency lifecycle management and makes the code cleaner and more maintainable.

### MVVM and Clean Architecture
I applied the MVVM (Model-View-ViewModel) pattern and clean architecture principles, dividing the application into several layers: presentation, domain, and data. This ensures high scalability, testability, and maintainability of the code.

- **Presentation Layer**: Contains UI components such as search and results screens, as well as ViewModel for managing UI state.
- **Domain Layer**: Includes use cases and business logic, ensuring the execution of the main operations of the application.
- **Data Layer**: Contains repositories, data sources, and data models used for network and caching operations.

### Modularization
I implemented a modular architecture by dividing the application into several modules: app, core, data, domain, feature-main, feature-search, and feature-tickets. This improves component isolation, facilitates testing, and promotes code reuse. This approach also speeds up the build process and enhances project scalability.

### User Interface
I used traditional XML layouts for creating the user interface, providing flexibility and control over each UI element. All fonts, colors, and icons are taken from Figma designs to ensure design consistency.

- **UI Components**: The application includes several screens, including the main search screen, results screen, and modal input dialogs.
- **Navigation**: Navigation is implemented using FragmentTransaction, allowing easy management of screen transitions and ensuring a smooth user experience.

### Data Storage
I used Shared Preferences to save user input and preferences. This allows retaining user settings and data even after the application is restarted.

### Offer List
The application displays ticket offers in a list using RecyclerView. Data for the offers is retrieved from the server response and displayed in the order received.

### Logging and Debugging
I paid special attention to logging and error handling to simplify the debugging process and ensure stable application performance.

### Build
Standard build process: (Build -> Build App Bundle(s) / APK(s) -> Build APK(s))

I hope this project demonstrates my skills and experience in developing Android applications using Kotlin. I would be happy to answer any questions or discuss further details. Thank you for considering my application!
