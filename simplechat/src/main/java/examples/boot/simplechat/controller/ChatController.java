package examples.boot.simplechat.controller;

import examples.boot.simplechat.domain.ChatRoom;
import examples.boot.simplechat.domain.ChatUser;
import examples.boot.simplechat.domain.User;
import examples.boot.simplechat.service.ChatService;
import examples.boot.simplechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/chatrooms")
public class ChatController {
    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;


    @GetMapping
    public String chatRooms(ModelMap modelMap){

        List<ChatRoom> list = chatService.getChatRooms();
        modelMap.put("list", list);

        return "chat/chatrooms";
    }

    @GetMapping(path = "/{id}")
    public String chatroom(Principal principal,  @PathVariable(name = "id") Long id, ModelMap modelMap){
        User user = userService.getUserByEmail(principal.getName());
        ChatRoom chatRoom = chatService.getChatRoom(id);
        List<ChatUser> chatUserList = chatRoom.getChatUsers();

        boolean findChatUser = false;
        for(ChatUser chatUser : chatUserList){
            if(chatUser.getUser().getId() == user.getId()){
                break;
            }else{
                findChatUser = true;
                break;
            }
        }

        if(!findChatUser){
            ChatUser chatUser = new ChatUser();
            chatUser.setChatRoom(chatRoom);
            chatService.addChatUser(chatUser);
        }

        modelMap.addAttribute("chatRoom", chatRoom);
        return "chat/chatroom";
    }

    @PostMapping
    public String create(Principal principal, @RequestParam(name = "title")String title){
        User user = userService.getUserByEmail(principal.getName());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setTitle(title);

        ChatUser chatUser = new ChatUser();
        chatUser.setUser(user);
        chatUser.setChatRoom(chatRoom);

        chatService.addChatRoom(chatRoom);
        return "redirect:/chatrooms";
    }

    @GetMapping("/createform")
    public String create(){
        return "chat/createform";
    }
}
