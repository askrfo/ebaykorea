package examples.boot.simplechat.service;

import examples.boot.simplechat.domain.ChatRoom;
import examples.boot.simplechat.domain.ChatUser;

import java.util.List;

public interface ChatService {
    public List<ChatRoom> getChatRooms();
    public void addChatRoom(ChatRoom chatRoom);
    public void addChatUser(ChatUser chatUser);
    public ChatRoom getChatRoom(Long id);
}
