package com.hantsylabs.example.conference.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hantsylabs.example.conference.model.Conference;

public class JdbcConferenceDaoImpl extends JdbcDaoSupport implements
		ConferenceDao {
	private static final Logger log = LoggerFactory
			.getLogger(JdbcConferenceDaoImpl.class);

	@Autowired
	DataSource dataSource;

	@Override
	public Conference findById(Long id) {
		return getJdbcTemplate().queryForObject(
				"select conference where id =?", new Object[] { id },
				new ConferenceMapper());

	}

	@Override
	public Long save(final Conference conference) {
		return getJdbcTemplate()
				.execute(
						"insert into conference('slug', 'description', started_date, ended_date,'contact_person', 'contact_email', 'contact_address' )values(?, ?, ?, ?, ?, ?, ?, ?) ",
						new PreparedStatementCallback<Long>() {

							@Override
							public Long doInPreparedStatement(
									PreparedStatement ps) throws SQLException,
									DataAccessException {
								ps.setString(1, conference.getSlug());
								ps.setString(2, conference.getDescription());
								ps.setDate(3, new java.sql.Date(conference
										.getStartedDate().getTime()));
								ps.setDate(4, new java.sql.Date(conference
										.getEndedDate().getTime()));
								ps.setString(5, conference.getContactPerson());
								ps.setString(6, conference.getContactEmail());
								ps.setString(7, conference.getContactAddress());
								ps.execute();
								ResultSet rs = ps.getGeneratedKeys();
								Long id = rs.getLong("id");
								log.debug("generated keys:" + id);
								return id;
							}
						});
	}

	@Override
	public void update(final Conference conference) {
		getJdbcTemplate()
				.update("update conference set slug=?, description=?, started_date=?, ended_date=?, contact_person=?, contact_email=?, contact_address=? where id =? ",
						new PreparedStatementSetter() {

							@Override
							public void setValues(PreparedStatement ps)
									throws SQLException {
								ps.setString(1, conference.getSlug());
								ps.setString(2, conference.getDescription());
								ps.setDate(3, new java.sql.Date(conference
										.getStartedDate().getTime()));
								ps.setDate(4, new java.sql.Date(conference
										.getEndedDate().getTime()));
								ps.setString(5, conference.getContactPerson());
								ps.setString(6, conference.getContactEmail());
								ps.setString(7, conference.getContactAddress());
								ps.setLong(8, conference.getId());
							}
						});
	}

	@Override
	public void delete(final Long id) {
		getJdbcTemplate().execute("delete from conference where id=?",
				new PreparedStatementCallback<Boolean>() {

					@Override
					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setLong(1, id);
						return ps.execute();
					}
				});
	}

	private class ConferenceMapper implements
			ParameterizedRowMapper<Conference> {

		@Override
		public Conference mapRow(ResultSet rs, int rowNum) throws SQLException {
			Conference conference = new Conference();
			conference.setContactAddress(rs.getString("contact_address"));
			conference.setContactEmail(rs.getString("contact_email"));
			conference.setContactPerson(rs.getString("contact_person"));
			conference.setName(rs.getString("name"));
			conference.setDescription(rs.getString("description"));
			conference.setSlug(rs.getString("slug"));
			conference.setStartedDate(rs.getDate("started_date"));
			conference.setEndedDate(rs.getDate("ended_date"));
			return conference;
		}
	}

}
