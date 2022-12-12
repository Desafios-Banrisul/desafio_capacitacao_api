package Get;

import TestBases.ConsultarCepInvalidoTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetConsultarCepInvalido extends ConsultarCepInvalidoTestBase {
    @DisplayName("Consultar CEP Inválido")
    @Test
    public void consultarCepInvvalido() {
        given()
                .spec(requestSpec)
        .when()
                .get()
        .then()
                .log().all()
                .spec(responseSpec);
    }
}
