import spock.lang.Specification

class SpeakerTest extends Specification {
    def "speaker speak"(){
        given:
            def speaker = new Speaker()
        expect:
            speaker.speak() == "HelloWorld!"
    }
}
