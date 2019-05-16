package com.example.aitor.myapplication;
import android.content.Context;
import android.location.LocationManager;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Rabbit {
    ConnectionFactory factory;
    Connection connection;
    int id;
    MainActivity main;
    public Rabbit(int id, MainActivity main){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        factory = new ConnectionFactory();
        factory.setUsername("dummy");
        factory.setPassword("dummy");
        factory.setHost("172.17.18.41");
        this.id=id;
        this.main = main;
        init();
    }

    private void init() {
        try {
            connection = factory.newConnection();
            Channel common = connection.createChannel();
            common.exchangeDeclare("common","fanout");
            String queueName = common.queueDeclare("common",false,false,false,null).getQueue();
            common.queueBind(queueName,"common","");
            MiConsumer consumer = new MiConsumer(common, main);
            common.basicConsume(queueName, true, consumer);

            LocationSender sender = new LocationSender(id);
            sender.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("a",e.toString());
        } catch (TimeoutException e) {
            e.printStackTrace();
            Log.e("a",e.toString());
        }
    }

    public class LocationSender extends Thread{
        int id;
        float lat, lng = 0;
        boolean changed = false;
        ConnectionFactory factory;
        Connection connection;
        Channel sender;
        public LocationSender(int id){
            this.id = id;
            factory = new ConnectionFactory();
            factory.setUsername("dummy");
            factory.setPassword("dummy");
            factory.setHost("172.17.18.41");
            try {
                connection = factory.newConnection();
                sender = connection.createChannel();
                sender.exchangeDeclare("LocationSender"+String.valueOf(id),"fanout");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            while(true){
                String s = String.valueOf(main.common.getLocation().getLatitude())+","+ String.valueOf(main.common.getLocation().getLongitude());
                try {
                    sender.basicPublish("LocationSender"+String.valueOf(id),"", null,s.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MiConsumer extends DefaultConsumer {

        MainActivity mainActivity;
        public MiConsumer(Channel channel, MainActivity main) {
            super(channel);
            this.mainActivity=main;
        }

        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body)throws IOException{
            String message= new String(body,Charset.defaultCharset());
            ArrayList<Incidencia> lista = main.common.parseXML(message);
            main.mainHandler.post(new Runnable(){
                @Override
                public void run() {
                    main.setMap(lista);
                }
            });
        }
    }
}
