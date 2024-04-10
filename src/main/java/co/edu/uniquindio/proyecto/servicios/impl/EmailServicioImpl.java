package co.edu.uniquindio.proyecto.servicios.impl;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class EmailServicioImpl implements EmailServicio {

    public ArrayList<String> tokens = new ArrayList<>();

    private final JavaMailSender javaMailSender;

    /**
     * envio de correo normal puede servir para notificaciones
     *
     * @param emailDTO
     * @throws Exception
     */
    @Override
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDTO.asunto());
        helper.setText(emailDTO.cuerpo(), true);
        helper.setTo(emailDTO.destinatario());
        helper.setFrom("no_reply@dominio.com");
        javaMailSender.send(mensaje);
    }

    @Override
    public String enviarTokenRecuperacion(String destinatario) throws Exception {
        return null;
    }

}
