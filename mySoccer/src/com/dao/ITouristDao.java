package com.dao;

import java.util.List;

import com.domain.CollectionItem;
import com.domain.TNews;
import com.domain.Tourist;

public interface ITouristDao {

	public void touristRegist(Tourist tourist);

	public Tourist touristLogin(Tourist tourist);

	public void createTNews(TNews tnews);

	public Integer findTotalTNews(Tourist tourist);

	public List<TNews> findOwnTNews(Tourist tourist, int begin, Integer pageSize);

	public TNews findTNewsArticle(String tId);

	public void deleteTNewsById(String tId);

	public void updateTNews(TNews tnews);

	public Tourist findTourist(String gettId);

	public void updateTourist(Tourist tourist);

	public List<TNews> findTNewsForInfo(Tourist tourist);

	public void addCollection(CollectionItem collectionItem);

	public void deleteCollection(int i, CollectionItem collectionItem);

	public CollectionItem findCollection(int i, CollectionItem collectionItem);

	public List<CollectionItem> findAllCollectionByType(int i, Tourist tourist);

	public Tourist findTouristByNumber(String touristEmail);

}
