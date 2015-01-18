NeoSpeech Java Client
=====================

This is just a trivial example of a Spring Boot application and a MVC configuration to call an external REST service.
The application connects to NeoSpeech https://tts.neospeech.com/rest_1_1.php REST endpoint, submits an XML payload containing the request for a text-to-speech conversion.

*N.B.*: NeoSpeech provides a free subscription with limited functions. You have to subscribe to NeoSpeech first and then provide your personal credentials modifying the application.properties.

Usage
-----

- Configure application.properties with your credentials (just ones)
- Compile and run the application
- Connect to http://localhost:8080/simple
- Enter your text
- Choose the voice
- Submit

You'll see the NeoSpeech response.

References
----------

Visit NeoSpeech site and download https://ws.neospeech.com/NeoSpeech-TTSWS-API-1.1.pdf for NeoSpeech APIs