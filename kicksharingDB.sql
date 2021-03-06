PGDMP     5                    z            kicksharingDB    14.0    14.0     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    25137    kicksharingDB    DATABASE     l   CREATE DATABASE "kicksharingDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "kicksharingDB";
                postgres    false            ?           1247    25171    enum    TYPE     C   CREATE TYPE public.enum AS ENUM (
    'AVAILABLE',
    'RENTED'
);
    DROP TYPE public.enum;
       public          postgres    false            ?            1259    25156    kickscooters    TABLE     ?   CREATE TABLE public.kickscooters (
    kickscooter_id bigint NOT NULL,
    name character(4) NOT NULL,
    status integer NOT NULL,
    user_id bigint
);
     DROP TABLE public.kickscooters;
       public         heap    postgres    false            ?            1259    25155    kickscooters_id_seq    SEQUENCE     |   CREATE SEQUENCE public.kickscooters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.kickscooters_id_seq;
       public          postgres    false    212                        0    0    kickscooters_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.kickscooters_id_seq OWNED BY public.kickscooters.kickscooter_id;
          public          postgres    false    211            ?            1259    25142    users    TABLE     ?   CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(32) NOT NULL,
    surname character varying(64) NOT NULL,
    age integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            ?            1259    25141    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    210                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    209            e           2604    25159    kickscooters kickscooter_id    DEFAULT     ~   ALTER TABLE ONLY public.kickscooters ALTER COLUMN kickscooter_id SET DEFAULT nextval('public.kickscooters_id_seq'::regclass);
 J   ALTER TABLE public.kickscooters ALTER COLUMN kickscooter_id DROP DEFAULT;
       public          postgres    false    212    211    212            d           2604    25145    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            ?          0    25156    kickscooters 
   TABLE DATA                 public          postgres    false    212          ?          0    25142    users 
   TABLE DATA                 public          postgres    false    210   ?                  0    0    kickscooters_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.kickscooters_id_seq', 9, true);
          public          postgres    false    211                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 4, true);
          public          postgres    false    209            i           2606    25163    kickscooters kickscooters_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.kickscooters
    ADD CONSTRAINT kickscooters_pkey PRIMARY KEY (kickscooter_id);
 H   ALTER TABLE ONLY public.kickscooters DROP CONSTRAINT kickscooters_pkey;
       public            postgres    false    212            g           2606    25149    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    210            j           2606    25200    kickscooters fk_user_id    FK CONSTRAINT     v   ALTER TABLE ONLY public.kickscooters
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);
 A   ALTER TABLE ONLY public.kickscooters DROP CONSTRAINT fk_user_id;
       public          postgres    false    3175    210    212            ?   ?   x??ѻ?@О???l?>x+?M&ZX)6?w?????v3Ӝbtݔ?t???7k6?5?3??Ǘ???]??????????,?3?p?W]?@$?g?y?`?"???B???R:?@!GA
)
?@?Q???-
?*puWU?DD?]?P?_!??K?      ?   ?   x??п?0?񝧸H???NN??QL???bJ!-|{???}o???cM[?8???av??b?,??3?8R֙_??Rx?Ϻ?$? ?8?H??r??(?_U??#??\x?h????v&46??UlfNc ϓR?{??n?Yy?&??{2(??ҫQ???g?     