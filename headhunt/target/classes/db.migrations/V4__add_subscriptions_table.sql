create table user_subscriptions (
    channel_id int8 not null references usr,
    subscriber_id int8 not null references usr,
    primary key (channel_id, subscriber_id)
);

alter table if exists user_subscriptions
    add CONSTRAINT channel_id FOREIGN KEY (channel_id) REFERENCES usr(id) on delete cascade ,
    add CONSTRAINT subscriber_id FOREIGN KEY (subscriber_id) REFERENCES usr(id) on delete cascade;

