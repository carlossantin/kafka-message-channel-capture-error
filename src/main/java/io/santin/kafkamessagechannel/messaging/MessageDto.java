package io.santin.kafkamessagechannel.messaging;

public class MessageDto {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                '}';
    }
}
