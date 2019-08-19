package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        List<TrelloListDto> listDtos1 = new ArrayList<>();
        List<TrelloListDto> listDtos2 = new ArrayList<>();
        listDtos1.add(new TrelloListDto("1","Math", false));
        listDtos1.add(new TrelloListDto("2", "History", false));
        listDtos2.add(new TrelloListDto("4", "Latin", true));
        listDtos2.add(new TrelloListDto("7", "Greek", false));
        boardDtos.add(new TrelloBoardDto("1", "Science", listDtos1));
        boardDtos.add(new TrelloBoardDto("3", "Languages", listDtos2));
        //When
        List<TrelloBoard> boards =trelloMapper.mapToBoards(boardDtos);
        //Then
        assertEquals(boardDtos.size(), boards.size());
        assertEquals(boardDtos.get(0).getId(), boards.get(0).getId());
        assertEquals(boardDtos.get(1).getName(), boards.get(1).getName());
        assertEquals(boardDtos.get(0).getLists().size(), boards.get(0).getLists().size());
        assertEquals(boardDtos.get(1).getLists().get(1).isClosed(), boards.get(1).getLists().get(1).isClosed());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        lists1.add(new TrelloList("1","Math", false));
        lists1.add(new TrelloList("3", "History", false));
        lists2.add(new TrelloList("4", "Latin", true));
        lists2.add(new TrelloList("5", "Greek", false));
        boards.add(new TrelloBoard("1", "Science", lists1));
        boards.add(new TrelloBoard("2", "Languages", lists2));
        //When
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(boards);
        //ThenDto
        assertEquals(boards.size(), boardDtos.size());
        assertEquals(boards.get(0).getId(), boardDtos.get(0).getId());
        assertEquals(boards.get(1).getName(), boardDtos.get(1).getName());
        assertEquals(boards.get(0).getLists().size(), boardDtos.get(0).getLists().size());
        assertEquals(boards.get(1).getLists().get(1).isClosed(), boardDtos.get(1).getLists().get(1).isClosed());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1","Math", false));
        listDtos.add(new TrelloListDto("2", "History", false));
        listDtos.add(new TrelloListDto("4", "Latin", true));
        listDtos.add(new TrelloListDto("7", "Greek", false));
        //When
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);
        //Then
        assertEquals(listDtos.size(), lists.size());
        assertEquals(listDtos.get(1).getId(), lists.get(1).getId());
        assertEquals(listDtos.get(2).getName(), lists.get(2).getName());
        assertEquals(listDtos.get(3).isClosed(), lists.get(3).isClosed());
    }

    @Test
    public void shouldMapToListEmptyListDto() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto());
        //When
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);
        //Then
        assertEquals(listDtos.size(), lists.size());
        assertEquals(listDtos.get(0).getId(), lists.get(0).getId());
        assertEquals(listDtos.get(0).getName(), lists.get(0).getName());
        assertEquals(listDtos.get(0).isClosed(), lists.get(0).isClosed());
    }

    @Test
    public void shouldMapToListsDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1","Math", false));
        lists.add(new TrelloList("3", "History", false));
        lists.add(new TrelloList("4", "Latin", true));
        lists.add(new TrelloList("5", "Greek", false));
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListsDto(lists);
        //Then
        assertEquals(lists.size(), listDtos.size());
        assertEquals(lists.get(0).getId(), listDtos.get(0).getId());
        assertEquals(lists.get(1).getName(), listDtos.get(1).getName());
        assertEquals(lists.get(2).isClosed(), listDtos.get(2).isClosed());
    }

    @Test
    public void shouldMapToListsDtoEmptyList() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList());
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListsDto(lists);
        //Then
        assertEquals(lists.size(), listDtos.size());
        assertEquals(lists.get(0).getId(), listDtos.get(0).getId());
        assertEquals(lists.get(0).getName(), listDtos.get(0).getName());
        assertEquals(lists.get(0).isClosed(), listDtos.get(0).isClosed());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Plural", "Plural Grammar Rules", "last","13" );
        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //Then
        assertEquals(cardDto.getName(), card.getName());
        assertEquals(cardDto.getDescription(), card.getDescription());
        assertEquals(cardDto.getPos(), card.getPos());
        assertEquals(cardDto.getListId(), card.getListId());
    }

    @Test
    public void shouldMapToCardEmptyCardDto() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto();
        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //Then
        assertEquals(cardDto.getName(), card.getName());
        assertEquals(cardDto.getDescription(), card.getDescription());
        assertEquals(cardDto.getPos(), card.getPos());
        assertEquals(cardDto.getListId(), card.getListId());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("Plural", "Plural Grammar Rules", "last","13" );
        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        //Then
        assertEquals(card.getName(), cardDto.getName());
        assertEquals(card.getDescription(), cardDto.getDescription());
        assertEquals(card.getPos(), cardDto.getPos());
        assertEquals(card.getListId(), cardDto.getListId());
    }

    @Test
    public void shouldMapToCardDtoEmptyCard() {
        //Given
        TrelloCard card = new TrelloCard();
        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        //Then
        assertEquals(card.getName(), cardDto.getName());
        assertEquals(card.getDescription(), cardDto.getDescription());
        assertEquals(card.getPos(), cardDto.getPos());
        assertEquals(card.getListId(), cardDto.getListId());
    }
}