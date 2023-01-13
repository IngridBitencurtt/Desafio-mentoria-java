package mentoria.desafio.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Parceiros {
    private int id;
    private String tradingName;
    private String ownerName;
    private String document;
    private String coverageArea;
    private String addressCoordinates;

    @Override
    public String toString(){
        return String.format("ID: %d , TRADING_NAME: %s   , OWNER_NAME: %s   , DOCUMENT: %s   , COVERAGE_AREA: %s  , ADDRESS_COORDINATES: %s",
                this.id,
                this.tradingName,
                this.ownerName,
                this.document,
                this.coverageArea,
                this.addressCoordinates);
    }

}

