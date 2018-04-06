package br.com.leucotron.livre.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import br.com.leucotron.livre.core.repository.BaseJdbcRepository;
import br.com.leucotron.livre.dto.FlowGroupDTO;
import br.com.leucotron.livre.dto.FlowOperationDTO;

/**
 * Repository for Flow Operations.
 * 
 * @author Virtus
 */
@Repository
public class FlowRepository extends BaseJdbcRepository {
	
	/**
	 * SQL for select groups and operations.
	 */
	private static final String SELECT_FLOW_GROUPS_SQL = "SELECT G.IDGRUPO, G.NOME AS NOMEGRUPO, OP.IDOPERACAO, OP.NOME AS NOMEOPERACAO, OP.ICONE " 
			+ "FROM GRUPO G INNER JOIN OPERACAO OP ON G.IDGRUPO = OP.IDGRUPO";

	/**
	 * Gets all groups with its operations for flow use.
	 * 
	 * @return All groups with its operations.
	 */
	public List<FlowGroupDTO> getFlowGroups() {
		final Map<Integer, FlowGroupDTO> map = new HashMap<>();

		getJdbcTemplate().query(SELECT_FLOW_GROUPS_SQL, new RowCallbackHandler() {

			/**
			 * (non-Javadoc)
			 * @see org.springframework.jdbc.core.RowCallbackHandler#processRow(java.sql.ResultSet)
			 */
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				int idGroup = rs.getInt("IDGRUPO");

				if(!map.containsKey(idGroup)) {
					map.put(idGroup, new FlowGroupDTO(rs.getInt("IDGRUPO"), rs.getString("NOMEGRUPO")));
				}

				map.get(idGroup).getOperations().add(
						new FlowOperationDTO(rs.getInt("IDOPERACAO"), rs.getString("NOMEOPERACAO"), rs.getString("ICONE")));
			}
		});

		return new ArrayList<>(map.values());
	}
}
