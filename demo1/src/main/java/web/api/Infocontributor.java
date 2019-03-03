package web.api;

import java.nio.file.attribute.AclEntry;
import java.util.HashMap;
import java.util.Map;
import Database.*;
public class Infocontributor {

    private TacoRepository tacoRepo;
    public Infocontributor(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    @Override
    public void contribute(AclEntry.Builder builder) {
        long tacoCount = tacoRepo.count();
        Map<String, Object> tacoMap = new HashMap<String, Object>();
        tacoMap.put("count", tacoCount);
        builder.withDetail("taco-stats", tacoMap);
    }

}
