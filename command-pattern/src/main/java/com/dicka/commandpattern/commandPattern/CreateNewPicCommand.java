package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Pic;
import com.dicka.commandpattern.model.PicRequest;

public interface CreateNewPicCommand extends Command<Pic, PicRequest> {
}
