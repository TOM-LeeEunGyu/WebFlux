package io.dustin.apps.board.api.user;

import io.dustin.apps.common.validation.UserCreateForm;
import io.dustin.apps.board.api.usecase.user.WriteUserUseCase;
import io.dustin.apps.board.domain.user.model.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final WriteUserUseCase writeUserUseCase;

    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // 양식을 안지키면
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            UserDto siteUser = writeUserUseCase.execute(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
//            return siteUser;
            return "siteUSer";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

    }
}
