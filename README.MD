#Unifocus backend tasks.
Source is built using maven in eclipse.
How to test FIRST Task:
- run AccurateWeather from your IDE
- open this link in http://localhost:8080/weather/forecast?cityName=Umag

How to test SECOND Task:
- First uncomment last line in application.properties (it will create new tables in DB).
- Run Unit test testSaveUserCities to insert some test data.
- open this link in web browser http://localhost:8080/weather/forecastDbUname?username=mirko_pw

How to test SIXTH Task:
- Run Unit test testWeatherFetchByUsername
