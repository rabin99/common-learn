package datastream.eventtime;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;


import java.io.IOException;

/**
 * @author linjh
 * @date 2019/2/16 16:49
 */
class MyDeserializationSchema implements DeserializationSchema<MyEvent> {
    @Override
    public MyEvent deserialize(byte[] message) throws IOException {
        String event = new String(message);
        String[] split = event.split(",");
        if(split.length != 3){
            throw new IllegalArgumentException("The event object could not be constructed!");
        }
        return new MyEvent(split[0],Long.parseLong(split[1]),split[2]);
    }

    @Override
    public boolean isEndOfStream(MyEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<MyEvent> getProducedType() {
        return TypeInformation.of(new TypeHint<MyEvent>() {
            @Override
            public TypeInformation<MyEvent> getTypeInfo() {
                return super.getTypeInfo();
            }
        });
    }
}
