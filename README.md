# Travix Flights

## Requirements

### System Requirement

JDK 11 or later

### Parallelism

The two external endpoints are called in parallel. If more suppliers are added, they will be called in parallel too.

    if a call to a supplier fails
        then results from other suppliers will be included in the BusyFlightRespnse
        and the result from the failed supplier will be omitted 
        (no exception is throw, no error object is returned to the user)
        and the call will not be tried in the scope of the current request

### Sorting

The results are sorted by fare _from the lowest fare to the highest_

### Input Validation

#### BusyFlightRequest

    destination:
        if destination is shorter then 3 characters (or null)
        then exception is thrown

        if destination is longer than 3 characters
        then exception is thrown

        if the destination is null
        then exception is thrown

    origin:
        if origin is shorter then 3 characters (or null)
        then exception is thrown

        if origin is longer than 3 characters
        then exception is thrown

    departureDate:
        if it is not in ISO_LOCAL_DATE format
        then exception is thrown

    returnDate:
        if it is not in ISO_LOCAL_DATE format
        then exception is thrown

    numberOfPassangers:
        if it is greater than 4
        then exception is thrown

        if it is less than 1
        then expeption is thrown

### Type Conversion

#### BusyFlightsRequest --> CrazyAirRequest

| Input              | Output         | Remarks           |
|--------------------|----------------|-------------------|
| origin             | origin         |                   |
| destination        | destination    |                   |
| departureDate      | departureDate  | same date formats |
| returnDate         | returnDate     | same date formats |
| numberOfPassengers | passengerCount |                   |

#### BusyFlightsRequest --> ToughJetRequest

| Input              | Output         | Remarks                                         |
|--------------------|----------------|-------------------------------------------------|
| origin             | from           |                                                 |
| destination        | to             |                                                 |
| departureDate      | outboundDate   | same date formats                               |
| returnDate         | inboundDate    | same date formats                               |
| numberOfPassengers | numberOfAdults | assuming that children can be counted as adults |

#### CrazyAirResponse --> BusyFlightsResponse

| Input                  | Output                 | Remarks                                                            |
|------------------------|------------------------|--------------------------------------------------------------------|
| airline                | airline                |                                                                    |
| _none_                 | supplier               | fixed text: 'CrazyAir'                                             |
| price                  | fare                   | assuming that no currency conversion is needed                     |
| cabinclass             | _none_                 | not in use                                                         |
| departureAirportCode   | departureAirportCode   |                                                                    |
| destinationAirportCode | destinationAirportCode |                                                                    |
| departureDate          | departureDate          | format must be converted from ISO_LOCAL_DATE_TIME to ISO_DATE_TIME |
| arrivalDate            | arrivalDate            | format must be converted from ISO_LOCAL_DATE_TIME to ISO_DATE_TIME |

#### ToughJetResponse --> BusyFlightsResponse

| Input                | Output                 | Remarks                                                                                                                                                                                                                                        |
|----------------------|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| carrier              | airline                |                                                                                                                                                                                                                                                |
| _none_               | supplier               | fixed text: 'ToughJet'                                                                                                                                                                                                                         |
| basePrice            | fare                   | fare = round((basePrice - discount) * (1 + tax), 2) -- assumes that the fare is not negative and not greater than Double.MAX_VALUE / 100 and 2 decimal places means that not more than 2 decimal places (for example 1.1 is preferred to 1.10) |
| tax                  | fare                   | included in the fare, assumes that the tax is a percentage of the price, e.g. 5%                                                                                                                                                               |
| discount             | fare                   | included in the fare, assumes that the discount is a fix amount, e.g. £20                                                                                                                                                                      |
| departureAirportName | departureAirportCode   |                                                                                                                                                                                                                                                |
| arrivalAirportName   | destinationAirportCode |                                                                                                                                                                                                                                                |
| outboundDateTime     | departureDate          | format must be converted from ISO_INSTANT to ISO_DATE_TIME - assuming the time zone is UTC                                                                                                                                                     |
| inboundDateTime      | arrivalDate            | format must be converted from ISO_INSTANT to ISO_DATE_TIME - assuming the time zone is UTC                                                                                                                                                     |

## Sequence Diagram

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg" contentStyleType="text/css" height="514px" preserveAspectRatio="none"
style="width:1151px;height:514px;background:#FFFFFF;" version="1.1" viewBox="0 0 1151 514" width="1151px"
zoomAndPan="magnify"><defs/><g><rect height="250.1953" style="stroke:#000000;stroke-width:1.5;fill:none;" width="911.5" x="234" y="111.5625"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="32" x2="32" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="166" x2="166" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="345" x2="345" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="542" x2="542" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="707.5" x2="707.5" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="881.5" x2="881.5" y1="36.2969" y2="479.1563"/><line style="stroke:#181818;stroke-width:0.5;fill:none;stroke-dasharray:5.0,5.0;" x1="1060.5" x2="1060.5" y1="36.2969" y2="479.1563"/><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="55" x="5" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="41" x="12" y="24.9951">Caller</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="55" x="5" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="41" x="12" y="498.1514">Caller</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="135" x="99" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="121" x="106" y="24.9951">FlightsController</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="135" x="99" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="121" x="106" y="498.1514">FlightsController</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="203" x="244" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="189" x="251" y="24.9951">AirlineService: BusyFlights</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="203" x="244" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="189" x="251" y="498.1514">AirlineService: BusyFlights</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="170" x="457" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="156" x="464" y="24.9951">Other Airline Services</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="170" x="457" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="156" x="464" y="498.1514">Other Airline Services</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="139" x="638.5" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="125" x="645.5" y="24.9951">Request Mappers</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="139" x="638.5" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="125" x="645.5" y="498.1514">Request Mappers</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="188" x="787.5" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="174" x="794.5" y="24.9951">External Airline Services</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="188" x="787.5" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="174" x="794.5" y="498.1514">External Airline Services</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="150" x="985.5" y="5"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="136" x="992.5" y="24.9951">Response Mappers</text><rect fill="#E2E2F0" height="30.2969" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="150" x="985.5" y="478.1563"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="136" x="992.5" y="498.1514">Response Mappers</text><polygon fill="#181818" points="154.5,63.4297,164.5,67.4297,154.5,71.4297,158.5,67.4297" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="32.5" x2="160.5" y1="67.4297" y2="67.4297"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="39.5" y="62.3638">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="79" x="52.5" y="62.3638">json request</text><polygon fill="#181818" points="333.5,92.5625,343.5,96.5625,333.5,100.5625,337.5,96.5625" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="166.5" x2="339.5" y1="96.5625" y2="96.5625"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="173.5" y="91.4966">2</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="49" x="186.5" y="91.4966">request</text><path d="M234,111.5625 L334,111.5625 L334,118.6953 L324,128.6953 L234,128.6953 L234,111.5625 " fill="#EEEEEE" style="stroke:#000000;stroke-width:1.5;"/><rect fill="none" height="250.1953" style="stroke:#000000;stroke-width:1.5;" width="911.5" x="234" y="111.5625"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="55" x="249" y="124.6294">parallel</text><polygon fill="#181818" points="530,145.8281,540,149.8281,530,153.8281,534,149.8281" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="345.5" x2="536" y1="149.8281" y2="149.8281"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="8" x="352.5" y="144.7622">3</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="78" x="364.5" y="144.7622">parallel calls</text><polygon fill="#181818" points="696,174.9609,706,178.9609,696,182.9609,700,178.9609" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="542" x2="702" y1="178.9609" y2="178.9609"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="549" y="173.895">4</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="109" x="562" y="173.895">mapping request</text><polygon fill="#181818" points="553,204.0938,543,208.0938,553,212.0938,549,208.0938" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="547" x2="707" y1="208.0938" y2="208.0938"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="559" y="203.0278">5</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="129" x="572" y="203.0278">translated  requests</text><polygon fill="#181818" points="869.5,233.2266,879.5,237.2266,869.5,241.2266,873.5,237.2266" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="542" x2="875.5" y1="237.2266" y2="237.2266"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="549" y="232.1606">6</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="85" x="562" y="232.1606">external calls</text><polygon fill="#181818" points="553,262.3594,543,266.3594,553,270.3594,549,266.3594" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="547" x2="880.5" y1="266.3594" y2="266.3594"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="559" y="261.2935">7</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="90" x="572" y="261.2935">search results</text><polygon fill="#181818" points="1048.5,291.4922,1058.5,295.4922,1048.5,299.4922,1052.5,295.4922" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="542" x2="1054.5" y1="295.4922" y2="295.4922"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="549" y="290.4263">8</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="109" x="562" y="290.4263">mapping request</text><polygon fill="#181818" points="553,320.625,543,324.625,553,328.625,549,324.625" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="547" x2="1059.5" y1="324.625" y2="324.625"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="559" y="319.5591">9</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="132" x="572" y="319.5591">translated  response</text><polygon fill="#181818" points="356.5,349.7578,346.5,353.7578,356.5,357.7578,352.5,353.7578" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="350.5" x2="541" y1="353.7578" y2="353.7578"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="19" x="362.5" y="348.6919">10</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="4" x="385.5" y="348.6919"> </text><line style="stroke:#181818;stroke-width:1.0;" x1="345.5" x2="387.5" y1="389.8906" y2="389.8906"/><line style="stroke:#181818;stroke-width:1.0;" x1="387.5" x2="387.5" y1="389.8906" y2="402.8906"/><line style="stroke:#181818;stroke-width:1.0;" x1="346.5" x2="387.5" y1="402.8906" y2="402.8906"/><polygon fill="#181818" points="356.5,398.8906,346.5,402.8906,356.5,406.8906,352.5,402.8906" style="stroke:#181818;stroke-width:1.0;"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="18" x="352.5" y="384.8247">11</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="153" x="374.5" y="384.8247">aggregating and sorting</text><polygon fill="#181818" points="177.5,428.0234,167.5,432.0234,177.5,436.0234,173.5,432.0234" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="171.5" x2="344.5" y1="432.0234" y2="432.0234"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="18" x="183.5" y="426.9575">12</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="90" x="205.5" y="426.9575">search results</text><polygon fill="#181818" points="43.5,457.1563,33.5,461.1563,43.5,465.1563,39.5,461.1563" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="37.5" x2="165.5" y1="461.1563" y2="461.1563"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="17" x="49.5" y="456.0903">13</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="89" x="70.5" y="456.0903">json response</text><!--MD5=[df359ee26243a60cc69709ce5f20418f]
@startuml
autonumber

Caller -> FlightsController: json request
FlightsController -> "AirlineService: BusyFlights": request
group parallel
"AirlineService: BusyFlights" -> "Other Airline Services": parallel calls
"Other Airline Services" -> "Request Mappers": mapping request
"Other Airline Services" <- "Request Mappers": translated  requests
"Other Airline Services" -> "External Airline Services": external calls
"Other Airline Services" <- "External Airline Services": search results
"Other Airline Services" -> "Response Mappers": mapping request
"Other Airline Services" <- "Response Mappers": translated  response
"Other Airline Services" -> "AirlineService: BusyFlights"
end group
"AirlineService: BusyFlights" -> "AirlineService: BusyFlights": aggregating and sorting
FlightsController <- "AirlineService: BusyFlights": search results
Caller <- FlightsController: json response
@enduml

PlantUML version 1.2022.7beta2(Unknown compile time)
(GPL source distribution)
Java Runtime: Java(TM) SE Runtime Environment
JVM: Java HotSpot(TM) 64-Bit Server VM
Default Encoding: UTF-8
Language: en
Country: US
--></g></svg>
