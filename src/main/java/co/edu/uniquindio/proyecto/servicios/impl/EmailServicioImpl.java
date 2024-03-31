package co.edu.uniquindio.proyecto.servicios.impl;
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

    public ArrayList<String> tokens=new ArrayList<>();

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

    /**
     * Envio de correo con token para restablecer contraseña
     *
     * @param destinatario
     * @throws Exception
     */
    @Override
    public String enviarTokenRecuperacion(String destinatario) throws Exception {

        String nuevoToken = generarToken();


        EmailDTO emailDTO = new EmailDTO(destinatario);
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDTO.asunto());
        String cuerpo = generarCuerpoConCodigo(nuevoToken);
        helper.setText(cuerpo, true);
        helper.setTo(emailDTO.destinatario());
        helper.setFrom("no_reply@dominio.com");
        javaMailSender.send(mensaje);

        tokens.add(nuevoToken);

        return nuevoToken;
    }

    private String generarToken() {
        return UUID.randomUUID().toString();
    }
    private String generarCuerpoConCodigo(String cuerpo) {

        String codigo = generarCodigo();

        return cuerpo.replace("{codigo}", codigo);
    }
    private String generarCodigo() {

        String caracteres = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuv";

        int longitud = 7;

        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            codigo.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return codigo.toString();
    }
}
