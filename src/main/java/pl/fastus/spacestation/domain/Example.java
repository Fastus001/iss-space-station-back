
package pl.fastus.spacestation.domain;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Example {

    private String message;
    private Request request;
    private List<Response> response = null;
}
