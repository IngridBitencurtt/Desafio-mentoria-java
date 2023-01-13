# mentoria-Java

*Projeto Zé delivery (mentoria)* 


**1. Cadastrar Parceiro:**
Para cadastrar um parceiro é necessário utilizar o endpoint "/cadastrar" enviando um Json como o abaixo

```
{
  "id": 10, 
  "tradingName": "Adega da Cerveja - Pinheiros",
  "ownerName": "Ingrid",
  "document": "1432132123891/0001",
  "coverageArea": "10",
  "addressCoordinates": [100, 200]
}
```

**2. Buscar parceiro por id:**
Para buscar um parceiro por id é necessário utilizar o endpoint "/buscarPorId/<id>" substintuindo <id> pelo id do parceiro desejado.
  
**3.Bucas parceiros próximos:**
Para buscar um parceiro próximo é necessário utilizar o endpoint "/buscarPorProximidade/<log>/<lat>" substintuindo <log>/<lat> pela sua longitude e latitude. 


