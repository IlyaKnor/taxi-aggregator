Taxi-aggregator
================
Агрегатор сервисов такси
- выделен общий для всех агрегаторов интерфейс
- для взаимодействия с экземплярами, реализующими интерфейс использован паттерн "Стратегия"
- для проверки работоспособности частично выполнена реализация для одного из агрегаторов
- реализован сервис, имитирующий ответ, отдающий статические данные
- рализована ассинхронная отпрака запросов в агрегаторы такси с помощью Spring Reactive
