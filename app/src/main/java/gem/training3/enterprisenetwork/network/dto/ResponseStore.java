package gem.training3.enterprisenetwork.network.dto;

import java.util.Arrays;

/**
 * Created by huylv on 25/02/2016.
 */
public class ResponseStore  {
    Store[] content;

    @Override
    public String toString() {
        return "ResponseStore{" +
                "content=" + Arrays.toString(content) +
                '}';
    }

    public Store[] getContent() {
        return content;
    }

    public void setContent(Store[] content) {
        this.content = content;
    }

    public ResponseStore(Store[] content) {

        this.content = content;
    }
}
