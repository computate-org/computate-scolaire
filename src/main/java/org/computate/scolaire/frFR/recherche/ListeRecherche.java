package org.computate.scolaire.frFR.recherche; 

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.ModifiableSolrParams;

import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.search.SearchList
 * MotCle: classeNomSimpleListeRecherche
 */
public class ListeRecherche<DEV> extends ListeRechercheGen<DEV> {

	/**  
	 * {@inheritDoc}
	 * 
	 **/
	protected void _c(Couverture<Class<DEV>> c) {
		
	}

	/**
	 * Var.enUS: siteRequest_
	 * Ignorer: true
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * Var.enUS: store
	 */
	protected void _stocker(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * Var.enUS: populate
	 */
	protected void _peupler(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 */
	protected void _fields(List<String> c) {
	}

	/**
	 * {@inheritDoc}
	 **/
	protected void _solrQuery(SolrQuery o) {
	}

	public DEV get(Integer index) {
		return list.get(index);
	}

	/**
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: modifie_
	 * r.enUS: modified_
	 */
	public boolean next(String dt) {
		boolean next = false;
		long numFound = getSolrDocumentList().getNumFound();
		if(numFound > 0) {
			addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));
			_queryResponse(queryResponseCouverture);
			setQueryResponse(queryResponseCouverture.o);
			_solrDocumentList(solrDocumentListCouverture);
			setSolrDocumentList(solrDocumentListCouverture.o);
			list.clear();
			_list(list);
			next = true;
		}
		return next;
	}

	/**
	 * {@inheritDoc}
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: ClientSolr
	 * r.enUS: SolrClient
	 **/
	protected void _queryResponse(Couverture<QueryResponse> c) {
		if(solrQuery.getQuery() != null) {
			try {
				QueryResponse o = requeteSite_.getSiteContexte_().getClientSolr().query(solrQuery);
				c.o(o);
			} catch (SolrServerException | IOException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/ 
	protected void _solrDocumentList(Couverture<SolrDocumentList> c) {
		if(solrQuery.getQuery() != null) {
			SolrDocumentList o = queryResponse.getResults();
			c.o(o);
		}
	}

	/**
	 * r: peuplerPourClasse
	 * r.enUS: populateForClass
	 * r: stockerPourClasse
	 * r.enUS: storeForClass
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: peupler
	 * r.enUS: populate
	 * r: stocker
	 * r.enUS: store
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 */
	protected void _list(List<DEV> l) {
		if(solrQuery.getQuery() != null) {
			for(SolrDocument solrDocument : solrDocumentList) {
				try {
					DEV o = c.newInstance();
					MethodUtils.invokeMethod(o, "setRequeteSite_", requeteSite_);
					if(peupler)
						MethodUtils.invokeMethod(o, "peuplerPourClasse", solrDocument);
					if(stocker)
						MethodUtils.invokeMethod(o, "stockerPourClasse", solrDocument);
	//				MethodUtils.invokeMethod(o, "initLoinPourClasse", requeteSite_);
					l.add(o);
				} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
						| InvocationTargetException e) {
					ExceptionUtils.rethrow(e);
				}
			}
		}
	}

	public DEV first() {
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public int size() {
		return list.size();
	}

	/**
	 * enable/disable terms.
	 * 
	 * @param b
	 *            flag to indicate terms should be enabled. <br>
	 *            if b==false, removes all other terms parameters
	 * @return Current reference (<i>this</i>)
	 */
	public SolrQuery setTerms(boolean b) {
		return solrQuery.setTerms(b);
	}

	public boolean getTerms() {
		return solrQuery.getTerms();
	}

	public SolrQuery addTermsField(String field) {
		return solrQuery.addTermsField(field);
	}

	public String[] getTermsFields() {
		return solrQuery.getTermsFields();
	}

	public SolrQuery setTermsLower(String lower) {
		return solrQuery.setTermsLower(lower);
	}

	public String getTermsLower() {
		return solrQuery.getTermsLower();
	}

	public SolrQuery setTermsUpper(String upper) {
		return solrQuery.setTermsUpper(upper);
	}

	public String getTermsUpper() {
		return solrQuery.getTermsUpper();
	}

	public SolrQuery setTermsUpperInclusive(boolean b) {
		return solrQuery.setTermsUpperInclusive(b);
	}

	public boolean getTermsUpperInclusive() {
		return solrQuery.getTermsUpperInclusive();
	}

	public SolrQuery setTermsLowerInclusive(boolean b) {
		return solrQuery.setTermsLowerInclusive(b);
	}

	public boolean getTermsLowerInclusive() {
		return solrQuery.getTermsLowerInclusive();
	}

	public SolrQuery setTermsLimit(int limit) {
		return solrQuery.setTermsLimit(limit);
	}

	public int getTermsLimit() {
		return solrQuery.getTermsLimit();
	}

	public SolrQuery setTermsMinCount(int cnt) {
		return solrQuery.setTermsMinCount(cnt);
	}

	public int getTermsMinCount() {
		return solrQuery.getTermsMinCount();
	}

	public SolrQuery setTermsMaxCount(int cnt) {
		return solrQuery.setTermsMaxCount(cnt);
	}

	public int getTermsMaxCount() {
		return solrQuery.getTermsMaxCount();
	}

	public SolrQuery setTermsPrefix(String prefix) {
		return solrQuery.setTermsPrefix(prefix);
	}

	public String getTermsPrefix() {
		return solrQuery.getTermsPrefix();
	}

	public SolrQuery setTermsRaw(boolean b) {
		return solrQuery.setTermsRaw(b);
	}

	public boolean getTermsRaw() {
		return solrQuery.getTermsRaw();
	}

	public SolrQuery setTermsSortString(String type) {
		return solrQuery.setTermsSortString(type);
	}

	public String getTermsSortString() {
		return solrQuery.getTermsSortString();
	}

	public SolrQuery setTermsRegex(String regex) {
		return solrQuery.setTermsRegex(regex);
	}

	public String getTermsRegex() {
		return solrQuery.getTermsRegex();
	}

	public SolrQuery setTermsRegexFlag(String flag) {
		return solrQuery.setTermsRegexFlag(flag);
	}

	public String[] getTermsRegexFlags() {
		return solrQuery.getTermsRegexFlags();
	}

	/**
	 * Add field(s) for facet computation.
	 * 
	 * @param fields
	 *            Array of field names from the IndexSchema
	 * @return this
	 */
	public SolrQuery addFacetField(String... fields) {
		return solrQuery.addFacetField(fields);
	}

	/**
	 * Add field(s) for pivot computation.
	 * 
	 * pivot fields are comma separated
	 * 
	 * @param fields
	 *            Array of field names from the IndexSchema
	 * @return this
	 */
	public SolrQuery addFacetPivotField(String... fields) {
		return solrQuery.addFacetPivotField(fields);
	}

	/**
	 * Add a numeric range facet.
	 *
	 * @param field
	 *            The field
	 * @param start
	 *            The start of range
	 * @param end
	 *            The end of the range
	 * @param gap
	 *            The gap between each count
	 * @return this
	 */
	public SolrQuery addNumericRangeFacet(String field, Number start, Number end, Number gap) {
		return solrQuery.addNumericRangeFacet(field, start, end, gap);
	}

	/**
	 * Add a numeric range facet.
	 *
	 * @param field
	 *            The field
	 * @param start
	 *            The start of range
	 * @param end
	 *            The end of the range
	 * @param gap
	 *            The gap between each count
	 * @return this
	 */
	public SolrQuery addDateRangeFacet(String field, Date start, Date end, String gap) {
		return solrQuery.addDateRangeFacet(field, start, end, gap);
	}

	/**
	 * Add Interval Faceting on a field. All intervals for the same field should be
	 * included in the same call to this method. For syntax documentation see
	 * <a href=
	 * "https://wiki.apache.org/solr/SimpleFacetParameters#Interval_Faceting">Solr
	 * wiki</a>. <br>
	 * Key substitution, filter exclusions or other local params on the field are
	 * not supported when using this method, if this is needed, use the lower level
	 * {@link #add} method.<br>
	 * Key substitution IS supported on intervals when using this method.
	 * 
	 * 
	 * @param field
	 *            the field to add facet intervals. Must be an existing field and
	 *            can't be null
	 * @param intervals
	 *            Intervals to be used for faceting. It can be an empty array, but
	 *            it can't be <code>null</code>
	 * @return this
	 */
	public SolrQuery addIntervalFacets(String field, String[] intervals) {
		return solrQuery.addIntervalFacets(field, intervals);
	}

	/**
	 * Remove all Interval Facets on a field
	 * 
	 * @param field
	 *            the field to remove from facet intervals
	 * @return Array of current intervals for <code>field</code>
	 */
	public String[] removeIntervalFacets(String field) {
		return solrQuery.removeIntervalFacets(field);
	}

	/**
	 * get the facet fields
	 * 
	 * @return string array of facet fields or null if not set/empty
	 */
	public String[] getFacetFields() {
		return solrQuery.getFacetFields();
	}

	/**
	 * remove a facet field
	 * 
	 * @param name
	 *            Name of the facet field to be removed.
	 * 
	 * @return true, if the item was removed. <br>
	 *         false, if the facet field was null or did not exist.
	 */
	public boolean removeFacetField(String name) {
		return solrQuery.removeFacetField(name);
	}

	/**
	 * enable/disable faceting.
	 * 
	 * @param b
	 *            flag to indicate faceting should be enabled. <br>
	 *            if b==false, removes all other faceting parameters
	 * @return Current reference (<i>this</i>)
	 */
	public SolrQuery setFacet(boolean b) {
		return solrQuery.setFacet(b);
	}

	public SolrQuery setFacetPrefix(String prefix) {
		return solrQuery.setFacetPrefix(prefix);
	}

	public SolrQuery setFacetPrefix(String field, String prefix) {
		return solrQuery.setFacetPrefix(field, prefix);
	}

	/**
	 * add a faceting query
	 * 
	 * @param f
	 *            facet query
	 */
	public SolrQuery addFacetQuery(String f) {
		return solrQuery.addFacetQuery(f);
	}

	/**
	 * get facet queries
	 * 
	 * @return all facet queries or null if not set/empty
	 */
	public String[] getFacetQuery() {
		return solrQuery.getFacetQuery();
	}

	/**
	 * remove a facet query
	 * 
	 * @param q
	 *            the facet query to remove
	 * @return true if the facet query was removed false otherwise
	 */
	public boolean removeFacetQuery(String q) {
		return solrQuery.removeFacetQuery(q);
	}

	/**
	 * set the facet limit
	 * 
	 * @param lim
	 *            number facet items to return
	 */
	public SolrQuery setFacetLimit(int lim) {
		return solrQuery.setFacetLimit(lim);
	}

	/**
	 * get current facet limit
	 * 
	 * @return facet limit or default of 25
	 */
	public int getFacetLimit() {
		return solrQuery.getFacetLimit();
	}

	/**
	 * set facet minimum count
	 * 
	 * @param cnt
	 *            facets having less that cnt hits will be excluded from teh facet
	 *            list
	 */
	public SolrQuery setFacetMinCount(int cnt) {
		return solrQuery.setFacetMinCount(cnt);
	}

	/**
	 * get facet minimum count
	 * 
	 * @return facet minimum count or default of 1
	 */
	public int getFacetMinCount() {
		return solrQuery.getFacetMinCount();
	}

	/**
	 * Sets facet missing boolean flag
	 * 
	 * @param v
	 *            flag to indicate the field of {@link FacetParams#FACET_MISSING} .
	 * @return this
	 */
	public SolrQuery setFacetMissing(Boolean v) {
		return solrQuery.setFacetMissing(v);
	}

	/**
	 * get facet sort
	 * 
	 * @return facet sort or default of {@link FacetParams#FACET_SORT_COUNT}
	 */
	public String getFacetSortString() {
		return solrQuery.getFacetSortString();
	}

	/**
	 * set facet sort
	 * 
	 * @param sort
	 *            sort facets
	 * @return this
	 */
	public SolrQuery setFacetSort(String sort) {
		return solrQuery.setFacetSort(sort);
	}

	/**
	 * add highlight field
	 * 
	 * @param f
	 *            field to enable for highlighting
	 */
	public SolrQuery addHighlightField(String f) {
		return solrQuery.addHighlightField(f);
	}

	/**
	 * remove a field for highlighting
	 * 
	 * @param f
	 *            field name to not highlight
	 * @return <i>true</i>, if removed, <br>
	 *         <i>false</i>, otherwise
	 */
	public boolean removeHighlightField(String f) {
		return solrQuery.removeHighlightField(f);
	}

	/**
	 * get list of highlighted fields
	 * 
	 * @return Array of highlight fields or null if not set/empty
	 */
	public String[] getHighlightFields() {
		return solrQuery.getHighlightFields();
	}

	public SolrQuery setHighlightSnippets(int num) {
		return solrQuery.setHighlightSnippets(num);
	}

	public int getHighlightSnippets() {
		return solrQuery.getHighlightSnippets();
	}

	public SolrQuery setHighlightFragsize(int num) {
		return solrQuery.setHighlightFragsize(num);
	}

	public int getHighlightFragsize() {
		return solrQuery.getHighlightFragsize();
	}

	public SolrQuery setHighlightRequireFieldMatch(boolean flag) {
		return solrQuery.setHighlightRequireFieldMatch(flag);
	}

	public boolean getHighlightRequireFieldMatch() {
		return solrQuery.getHighlightRequireFieldMatch();
	}

	public SolrQuery setHighlightSimplePre(String f) {
		return solrQuery.setHighlightSimplePre(f);
	}

	public String getHighlightSimplePre() {
		return solrQuery.getHighlightSimplePre();
	}

	public SolrQuery setHighlightSimplePost(String f) {
		return solrQuery.setHighlightSimplePost(f);
	}

	public String getHighlightSimplePost() {
		return solrQuery.getHighlightSimplePost();
	}

	/**
	 * Gets the raw sort field, as it will be sent to Solr.
	 * <p>
	 * The returned sort field will always contain a serialized version of the sort
	 * string built using {@link #setSort(SortClause)},
	 * {@link #addSort(SortClause)}, {@link #addOrUpdateSort(SortClause)},
	 * {@link #removeSort(SortClause)}, {@link #clearSorts()} and
	 * {@link #setSorts(List)}.
	 */
	public String getSortField() {
		return solrQuery.getSortField();
	}

	/**
	 * Clears current sort information.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery clearSorts() {
		return solrQuery.clearSorts();
	}

	/**
	 * Replaces the current sort information.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery setSorts(List<SortClause> value) {
		return solrQuery.setSorts(value);
	}

	/**
	 * Gets an a list of current sort clauses.
	 *
	 * @return an immutable list of current sort clauses
	 * @since 4.2
	 */
	public List<SortClause> getSorts() {
		return solrQuery.getSorts();
	}

	/**
	 * Replaces the current sort information with a single sort clause
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery setSort(String field, ORDER order) {
		return solrQuery.setSort(field, order);
	}

	/**
	 * Replaces the current sort information with a single sort clause
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery setSort(SortClause sortClause) {
		return solrQuery.setSort(sortClause);
	}

	/**
	 * Adds a single sort clause to the end of the current sort information.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery addSort(String field, ORDER order) {
		return solrQuery.addSort(field, order);
	}

	/**
	 * Adds a single sort clause to the end of the query.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery addSort(SortClause sortClause) {
		return solrQuery.addSort(sortClause);
	}

	/**
	 * Updates or adds a single sort clause to the query. If the field is already
	 * used for sorting, the order of the existing field is modified; otherwise, it
	 * is added to the end.
	 * <p>
	 * 
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery addOrUpdateSort(String field, ORDER order) {
		return solrQuery.addOrUpdateSort(field, order);
	}

	/**
	 * Updates or adds a single sort field specification to the current sort
	 * information. If the sort field already exist in the sort information map, its
	 * position is unchanged and the sort order is set; if it does not exist, it is
	 * appended at the end with the specified order..
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery addOrUpdateSort(SortClause sortClause) {
		return solrQuery.addOrUpdateSort(sortClause);
	}

	/**
	 * Removes a single sort field from the current sort information.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery removeSort(SortClause sortClause) {
		return solrQuery.removeSort(sortClause);
	}

	/**
	 * Removes a single sort field from the current sort information.
	 *
	 * @return the modified SolrQuery object, for easy chaining
	 * @since 4.2
	 */
	public SolrQuery removeSort(String itemName) {
		return solrQuery.removeSort(itemName);
	}

	public void setGetFieldStatistics(boolean v) {
		solrQuery.setGetFieldStatistics(v);
	}

	public void setGetFieldStatistics(String field) {
		solrQuery.setGetFieldStatistics(field);
	}

	public void addGetFieldStatistics(String... field) {
		solrQuery.addGetFieldStatistics(field);
	}

	public void addStatsFieldFacets(String field, String... facets) {
		solrQuery.addStatsFieldFacets(field, facets);
	}

	public void addStatsFieldCalcDistinct(String field, boolean calcDistinct) {
		solrQuery.addStatsFieldCalcDistinct(field, calcDistinct);
	}

	public SolrQuery setFilterQueries(String... fq) {
		return solrQuery.setFilterQueries(fq);
	}

	public SolrQuery addFilterQuery(String... fq) {
		return solrQuery.addFilterQuery(fq);
	}

	public boolean removeFilterQuery(String fq) {
		return solrQuery.removeFilterQuery(fq);
	}

	public String[] getFilterQueries() {
		return solrQuery.getFilterQueries();
	}

	public boolean getHighlight() {
		return solrQuery.getHighlight();
	}

	public SolrQuery setHighlight(boolean b) {
		return solrQuery.setHighlight(b);
	}

	/**
	 * Add field for MoreLikeThis. Automatically enables MoreLikeThis.
	 *
	 * @param field
	 *            the names of the field to be added
	 * @return this
	 */
	public SolrQuery addMoreLikeThisField(String field) {
		return solrQuery.addMoreLikeThisField(field);
	}

	public SolrQuery setMoreLikeThisFields(String... fields) {
		return solrQuery.setMoreLikeThisFields(fields);
	}

	/**
	 * @return an array with the fields used to compute similarity.
	 */
	public String[] getMoreLikeThisFields() {
		return solrQuery.getMoreLikeThisFields();
	}

	/**
	 * Sets the frequency below which terms will be ignored in the source doc
	 *
	 * @param mintf
	 *            the minimum term frequency
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMinTermFreq(int mintf) {
		return solrQuery.setMoreLikeThisMinTermFreq(mintf);
	}

	/**
	 * Gets the frequency below which terms will be ignored in the source doc
	 */
	public int getMoreLikeThisMinTermFreq() {
		return solrQuery.getMoreLikeThisMinTermFreq();
	}

	/**
	 * Sets the frequency at which words will be ignored which do not occur in at
	 * least this many docs.
	 *
	 * @param mindf
	 *            the minimum document frequency
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMinDocFreq(int mindf) {
		return solrQuery.setMoreLikeThisMinDocFreq(mindf);
	}

	/**
	 * Gets the frequency at which words will be ignored which do not occur in at
	 * least this many docs.
	 */
	public int getMoreLikeThisMinDocFreq() {
		return solrQuery.getMoreLikeThisMinDocFreq();
	}

	/**
	 * Sets the minimum word length below which words will be ignored.
	 *
	 * @param minwl
	 *            the minimum word length
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMinWordLen(int minwl) {
		return solrQuery.setMoreLikeThisMinWordLen(minwl);
	}

	/**
	 * Gets the minimum word length below which words will be ignored.
	 */
	public int getMoreLikeThisMinWordLen() {
		return solrQuery.getMoreLikeThisMinWordLen();
	}

	/**
	 * Sets the maximum word length above which words will be ignored.
	 *
	 * @param maxwl
	 *            the maximum word length
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMaxWordLen(int maxwl) {
		return solrQuery.setMoreLikeThisMaxWordLen(maxwl);
	}

	/**
	 * Gets the maximum word length above which words will be ignored.
	 */
	public int getMoreLikeThisMaxWordLen() {
		return solrQuery.getMoreLikeThisMaxWordLen();
	}

	/**
	 * Sets the maximum number of query terms that will be included in any generated
	 * query.
	 *
	 * @param maxqt
	 *            the maximum number of query terms
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMaxQueryTerms(int maxqt) {
		return solrQuery.setMoreLikeThisMaxQueryTerms(maxqt);
	}

	/**
	 * Gets the maximum number of query terms that will be included in any generated
	 * query.
	 */
	public int getMoreLikeThisMaxQueryTerms() {
		return solrQuery.getMoreLikeThisMaxQueryTerms();
	}

	/**
	 * Sets the maximum number of tokens to parse in each example doc field that is
	 * not stored with TermVector support.
	 *
	 * @param maxntp
	 *            the maximum number of tokens to parse
	 * @return this
	 */
	public SolrQuery setMoreLikeThisMaxTokensParsed(int maxntp) {
		return solrQuery.setMoreLikeThisMaxTokensParsed(maxntp);
	}

	/**
	 * Gets the maximum number of tokens to parse in each example doc field that is
	 * not stored with TermVector support.
	 */
	public int getMoreLikeThisMaxTokensParsed() {
		return solrQuery.getMoreLikeThisMaxTokensParsed();	
	}

	/**
	 * Sets if the query will be boosted by the interesting term relevance.
	 *
	 * @param b
	 *            set to true to boost the query with the interesting term relevance
	 * @return this
	 */
	public SolrQuery setMoreLikeThisBoost(boolean b) {
		return solrQuery.setMoreLikeThisBoost(b);
	}

	/**
	 * Gets if the query will be boosted by the interesting term relevance.
	 */
	public boolean getMoreLikeThisBoost() {
		return solrQuery.getMoreLikeThisBoost();
	}

	/**
	 * Sets the query fields and their boosts using the same format as that used in
	 * DisMaxQParserPlugin. These fields must also be added using
	 * {@link #addMoreLikeThisField(String)}.
	 *
	 * @param qf
	 *            the query fields
	 * @return this
	 */
	public SolrQuery setMoreLikeThisQF(String qf) {
		return solrQuery.setMoreLikeThisQF(qf);
	}

	/**
	 * Gets the query fields and their boosts.
	 */
	public String getMoreLikeThisQF() {
		return solrQuery.getMoreLikeThisQF();
	}

	/**
	 * Sets the number of similar documents to return for each result.
	 *
	 * @param count
	 *            the number of similar documents to return for each result
	 * @return this
	 */
	public SolrQuery setMoreLikeThisCount(int count) {
		return solrQuery.setMoreLikeThisCount(count);
	}

	/**
	 * Gets the number of similar documents to return for each result.
	 */
	public int getMoreLikeThisCount() {
		return solrQuery.getMoreLikeThisCount();
	}

	/**
	 * Enable/Disable MoreLikeThis. After enabling MoreLikeThis, the fields used for
	 * computing similarity must be specified calling
	 * {@link #addMoreLikeThisField(String)}.
	 *
	 * @param b
	 *            flag to indicate if MoreLikeThis should be enabled. if b==false
	 *            removes all mlt.* Parameters
	 * @return this
	 */
	public SolrQuery setMoreLikeThis(boolean b) {
		return solrQuery.setMoreLikeThis(b);
	}

	/**
	 * @return true if MoreLikeThis is enabled, false otherwise
	 */
	public boolean getMoreLikeThis() {
		return solrQuery.getMoreLikeThis();
	}

	public SolrQuery setIncludeScore(boolean includeScore) {
		return solrQuery.setIncludeScore(includeScore);
	}

	public SolrQuery setQuery(String query) {
		return solrQuery.setQuery(query);
	}

	public String getQuery() {
		return solrQuery.getQuery();
	}

	public SolrQuery setRows(Integer rows) {
		return solrQuery.setRows(rows);
	}

	public Integer getRows() {
		return solrQuery.getRows();
	}

	public SolrQuery setShowDebugInfo(boolean showDebugInfo) {
		return solrQuery.setShowDebugInfo(showDebugInfo);
	}

	public void set(String name, boolean val) {
		solrQuery.set(name, val);
	}

	public void set(String name, int val) {
		solrQuery.set(name, val);
	}

	public void set(String name, String vals) {
		solrQuery.set(name, vals);
	}

	public void setDistrib(boolean val) {
		solrQuery.setDistrib(val);
	}

	public SolrQuery setStart(Integer start) {
		return solrQuery.setStart(start);
	}

	public Integer getStart() {
		return solrQuery.getStart();
	}

	/**
	 * The Request Handler to use (see the solrconfig.xml), which is stored in the
	 * "qt" parameter. Normally it starts with a '/' and if so it will be used by
	 * {@link org.apache.solr.client.solrj.request.QueryRequest#getPath()} in the
	 * URL instead of the "qt" parameter. If this is left blank, then the default of
	 * "/select" is assumed.
	 *
	 * @param qt
	 *            The Request Handler name corresponding to one in solrconfig.xml on
	 *            the server.
	 * @return this
	 */
	public SolrQuery setRequestHandler(String qt) {
		return solrQuery.setRequestHandler(qt);
	}

	public String getRequestHandler() {
		return solrQuery.getRequestHandler();
	}

	/**
	 * @return this
	 * @see ModifiableSolrParams#set(String,String[])
	 */
	public SolrQuery setParam(String name, String... values) {
		return solrQuery.setParam(name, values);
	}

	/**
	 * @return this
	 * @see org.apache.solr.common.params.ModifiableSolrParams#set(String, boolean)
	 */
	public SolrQuery setParam(String name, boolean value) {
		return solrQuery.setParam(name, value);
	}

	/** get a deep copy of this object **/
	public SolrQuery getCopy() {
		return solrQuery.getCopy();
	}

	/**
	 * Set the maximum time allowed for this query. If the query takes more time
	 * than the specified milliseconds, a timeout occurs and partial (or no) results
	 * may be returned.
	 * 
	 * If given Integer is null, then this parameter is removed from the request
	 * 
	 * @param milliseconds
	 *            the time in milliseconds allowed for this query
	 */
	public SolrQuery setTimeAllowed(Integer milliseconds) {
		return solrQuery.setTimeAllowed(milliseconds);
	}

	/**
	 * Get the maximum time allowed for this query.
	 */
	public Integer getTimeAllowed() {
		return solrQuery.getTimeAllowed();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ListeRecherche { ");
		list.stream().forEach(o -> {
			sb.append(o);
		});
		sb.append(" }");
		return sb.toString();
	}
}
