package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Pic;
import com.dicka.commandpattern.model.PicRequest;
import com.dicka.commandpattern.repository.PicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateNewPicCommandImpl extends AbstractCommand<Pic, PicRequest>
    implements CreateNewPicCommand{

    @Autowired
    private PicRepository picRepository;

    @Override
    public Pic doExecute(PicRequest request) {
        Pic pic = createNewPic(request);
        return picRepository.save(pic);
    }

    private Pic createNewPic(PicRequest request){
        return Pic.builder()
                .name(request.getPicName())
                .position(request.getPicPosition())
                .build();
    }
}
