package mentoria.desafio.repository;

import mentoria.desafio.model.Parceiros;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ParceirosRepository {

    private final JdbcTemplate jdbcTemplate;

    @Inject
    public ParceirosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void cadastrarParceiro(Parceiros parceiros) {

        var queryCadastrarParceiro = "INSERT INTO PARCEIROS (ID, TRADING_NAME, OWNER_NAME, DOCUMENT, COVERAGE_AREA, ADDRESS_COORDINATES)" +
                " VALUES( ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(queryCadastrarParceiro
                , parceiros.getId()
                , parceiros.getTradingName()
                , parceiros.getOwnerName()
                , parceiros.getDocument()
                , parceiros.getCoverageArea()
                , parceiros.getAddressCoordinates());
    }

    public Parceiros buscarPorId(int id){

        var queryBuscarPorId = "SELECT ID, TRADING_NAME, OWNER_NAME, DOCUMENT, COVERAGE_AREA, ADDRESS_COORDINATES FROM PARCEIROS WHERE ID = ?";
        return jdbcTemplate.queryForObject(queryBuscarPorId,
                (rs, rowNum) ->  Parceiros.builder()
                .id(rs.getInt("ID"))
                .tradingName(rs.getString("TRADING_NAME"))
                .ownerName(rs.getString("OWNER_NAME"))
                .document(rs.getString("DOCUMENT"))
                .coverageArea(rs.getString("COVERAGE_AREA"))
                .addressCoordinates(rs.getString("ADDRESS_COORDINATES"))
                .build()
                ,id);
    }

    public Parceiros buscarPorProximidade(int log, int lat) {
        var queryBuscarPorProximidade = "SELECT ID, TRADING_NAME, OWNER_NAME, DOCUMENT, COVERAGE_AREA, ADDRESS_COORDINATES FROM PARCEIROS WHERE ADDRESS_COORDINATE = ?";
        //List<Parceiros> listaParceiros = new ArrayList<>();
        List<Map<String, Object>> listaParceiros = jdbcTemplate.queryForList("SELECT * FROM PARCEIROS");

        for (Map row : listaParceiros) {
            Parceiros obj = Parceiros.builder()
                    .id(Integer.parseInt(row.get("ID").toString()))
                    .tradingName(row.get("TRADING_NAME").toString())
                    .ownerName(row.get("OWNER_NAME").toString())
                    .document(row.get("DOCUMENT").toString())
                    .coverageArea(row.get("COVERAGE_AREA").toString())
                    .addressCoordinates(row.get("ADDRESS_COORDINATES").toString())
                    .build();

            var coordenadas = obj.getAddressCoordinates().split(",");

            double log_address = Double.parseDouble(coordenadas[0].replace("[","").trim());
            double lat_address = Double.parseDouble(coordenadas[1].replace("]","").trim());

            // Logica de proximidade para saber se está dentro da area de cobertura
            if (log <= (log_address + Integer.parseInt(obj.getCoverageArea())) &&
                    (log >= (log_address - Integer.parseInt(obj.getCoverageArea())))) {
                if (lat <= (lat_address + Integer.parseInt(obj.getCoverageArea())) &&
                        (lat >= (lat_address - Integer.parseInt(obj.getCoverageArea())))) {
                    return obj;
                }
            }
        }
        return  null;
    }
}