
package pl.fastus.spacestation.domain.apipass;

import lombok.Data;

import java.util.List;

@Data
public class ApiPass {

    private String message;
    private Request request;
    private List<Response> response = null;
}
