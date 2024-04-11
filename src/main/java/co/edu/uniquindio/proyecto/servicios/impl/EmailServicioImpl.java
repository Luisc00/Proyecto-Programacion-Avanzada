package co.edu.uniquindio.proyecto.servicios.impl;
import co.edu.uniquindio.proyecto.dto.DetalleClienteDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class EmailServicioImpl implements EmailServicio {

    @Autowired
    private JWTUtils jwtUtils;

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
        // Verificar que jwtUtils no sea null antes de usarlo
        if (jwtUtils == null) {
            throw new IllegalStateException("JWTUtils no ha sido inicializado correctamente");
        }

        // Generar el token de recuperación utilizando JWTUtils
        String token = jwtUtils.generarToken(destinatario, null);

        // Enviar el token al correo electrónico del usuario
        String cuerpoCorreo = "Utilice el siguiente token para restablecer su contraseña: " + token;
        EmailDTO emailDTO = new EmailDTO("Recuperación de contraseña", cuerpoCorreo, destinatario);
        enviarCorreo(emailDTO);

        return token;
    }
}

