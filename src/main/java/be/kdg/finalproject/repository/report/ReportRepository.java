package be.kdg.finalproject.repository.report;

import be.kdg.finalproject.domain.report.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
	@Query("""
	select rep
	from REPORTS rep
	join IDEAS id on rep.ideaId=id.id
	join CALL_FOR_IDEAS call on id.callForIdeasId=call.id AND call.municipality.id=:municipalityId
""")
	Set<Report> getReportsByMunicipalityId(long municipalityId);
}
